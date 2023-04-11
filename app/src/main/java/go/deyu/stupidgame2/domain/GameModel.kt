package go.deyu.stupidgame2.domain

import com.google.gson.Gson
import com.orhanobut.logger.Logger
import go.deyu.stupidgame2.data.model.*
import go.deyu.stupidgame2.domain.usecase.CreateNewGameUseCase

class GameModel(
    private val createNewGameUseCase: CreateNewGameUseCase,
) {
    suspend fun requestNewGameData(): GameData? {
        val result = createNewGameUseCase()
        Logger.e("result = $result")
        return try {
            val gameData = result.getOrNull()?.choices?.last()?.message?.content?.let {
                Gson().fromJson(it, GameData::class.java)
            }
            Logger.e("gameData = $gameData")
            gameData
        } catch (e: Exception) {
            Logger.e("e = $e")
            null
        }
    }

}

data class GameState(
    val isLoading: Boolean = false,
    val guesses: Int = 0,
    val gameData: GameData?  = null,
    val guessResult: GuessResult? = null
)