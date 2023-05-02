package go.deyu.stupidgame2.presentation.game

import go.deyu.stupidgame2.data.model.GameData
import go.deyu.stupidgame2.data.model.GuessResult

sealed class GameScreenState {
    object Entrance : GameScreenState()
    data class InProgress(val gameData: GameData) : GameScreenState()
    data class GameOver(val guessResult: GuessResult) : GameScreenState()
}