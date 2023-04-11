package go.deyu.stupidgame2.presentation.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import go.deyu.stupidgame2.domain.GameModel
import go.deyu.stupidgame2.domain.GameState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val gameModel: GameModel) : ViewModel() {
    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    private val _intent = Channel<GameIntent>(Channel.UNLIMITED)
    val intent: SendChannel<GameIntent> = _intent

    init {
        viewModelScope.launch {
            _intent.consumeAsFlow().collect { intent ->
                when (intent) {
                    GameIntent.NewGame -> newGame()
                    else -> {}
                }
            }
        }
    }

    // 新增的 sendIntent 方法
    fun sendIntent(intent: GameIntent) {
        viewModelScope.launch {
            _intent.send(intent)
        }
    }


    private suspend fun newGame() {
        _state.value = state.value.copy(
            isLoading = true
        )
        _state.value = state.value.copy(
            gameData = gameModel.requestNewGameData(),
            isLoading = false
        )
    }


}