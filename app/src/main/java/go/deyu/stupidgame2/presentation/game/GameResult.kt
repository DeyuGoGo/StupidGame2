package go.deyu.stupidgame2.presentation.game

import go.deyu.stupidgame2.data.model.Answer
import go.deyu.stupidgame2.data.model.Clue
import go.deyu.stupidgame2.data.model.GuessResult
import go.deyu.stupidgame2.data.model.Theme

sealed class GameResult {
    object Loading : GameResult()
    data class ThemeLoaded(val theme: Theme) : GameResult()
    data class QuestionAnswered(val answer: Answer) : GameResult()
    data class ClueFetched(val clue: Clue) : GameResult()
    data class MurdererGuessed(val guessResult: GuessResult) : GameResult()
    data class Error(val error: Throwable) : GameResult()
}