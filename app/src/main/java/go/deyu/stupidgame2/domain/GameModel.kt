package go.deyu.stupidgame2.domain

import com.google.gson.Gson
import com.orhanobut.logger.Logger
import go.deyu.stupidgame2.data.model.*
import go.deyu.stupidgame2.domain.usecase.RequestChatUseCase
import go.deyu.stupidgame2.domain.usecase.RequestImageUseCase
import go.deyu.stupidgame2.domain.usecase.RequestNewGameUseCase
import go.deyu.stupidgame2.presentation.game.GameScreenState

class GameModel(
    private val requestNewGameUseCase: RequestNewGameUseCase
) {

    private val _messageList = mutableListOf<Message>()

    fun reset() {
        _messageList.clear()
    }

    suspend fun requestNewGameData(): GameData? {
        reset()
        val startMessage = MessageBook.getNewGameMessage()
        return try {
            val gameData = requestNewGameUseCase()
            Logger.e("gameData = $gameData")
            _messageList.add(startMessage)
            _messageList.add(Message(content = Gson().toJson(gameData) , role = "assistant"))
            gameData
        } catch (e: Exception) {
            Logger.e("e = $e")
            null
        }
    }

}

data class GameState(
    val isLoading: Boolean = false,
    val screenState: GameScreenState = GameScreenState.Entrance
)