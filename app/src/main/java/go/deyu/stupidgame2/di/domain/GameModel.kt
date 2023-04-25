package go.deyu.stupidgame2.di.domain

import com.google.gson.Gson
import com.orhanobut.logger.Logger
import go.deyu.stupidgame2.data.model.*
import go.deyu.stupidgame2.di.domain.usecase.RequestChatUseCase
import go.deyu.stupidgame2.di.domain.usecase.RequestImageUseCase
import go.deyu.stupidgame2.presentation.game.GameScreenState

class GameModel(
    private val requestChatUseCase: RequestChatUseCase,
    private val requestImageUseCase: RequestImageUseCase
) {

    private val _messageList = mutableListOf<Message>()
    private val messageList: List<Message> = _messageList

    fun reset() {
        _messageList.clear()
    }

    suspend fun requestNewGameData(): GameData? {
        reset()
        val startMessage = MessageBook.getNewGameMessage()
        val result = requestChatUseCase(listOf(startMessage))
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

    suspend fun requestGameOverMessage(suspect: Suspect ): GuessResult? {
        val overMessage = MessageBook.getGuessMessage(suspect.name)
        _messageList.add(overMessage)
        val result = requestChatUseCase(messageList)
        return try {
            val message = result.getOrNull()?.choices?.last()?.message!!.content
            GuessResult(
                isCorrect = message.contains("猜對"),
                message = message
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