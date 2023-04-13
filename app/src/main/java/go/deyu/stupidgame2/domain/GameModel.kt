package go.deyu.stupidgame2.domain

import com.google.gson.Gson
import com.orhanobut.logger.Logger
import go.deyu.stupidgame2.data.model.*
import go.deyu.stupidgame2.domain.usecase.CreateNewGameUseCase
import go.deyu.stupidgame2.presentation.game.GameScreenState

class GameModel(
    private val createNewGameUseCase: CreateNewGameUseCase,
) {

    private val _messageList = mutableListOf<Message>()
    val messageList: List<Message> = _messageList

    fun reset() {
        _messageList.clear()
    }

    suspend fun requestNewGameData(): GameData? {
        val startMessage = MessageBook.getNewGameMessage()
        val result = createNewGameUseCase(startMessage)
        Logger.e("result = $result")
        return try {
            val newGameMessage = result.getOrNull()?.choices?.last()?.message
            val gameData = newGameMessage!!.content.let {
                Gson().fromJson(it, GameData::class.java)
            }
            Logger.e("gameData = $gameData")
            _messageList.add(startMessage)
            _messageList.add(newGameMessage)
            gameData
        } catch (e: Exception) {
            Logger.e("e = $e")
            null
        }
    }

    suspend fun requestGameOverMessage(suspect: Suspect): GuessResult? {
        val overMessage = MessageBook.getGuessMessage(suspect.name)
        val result = createNewGameUseCase(overMessage)
        Logger.e("result = $result")
        return try {
            val newGameMessage = result.getOrNull()?.choices?.last()?.message
            GuessResult(
                isCorrect = true,
                message = newGameMessage!!.content
            )
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