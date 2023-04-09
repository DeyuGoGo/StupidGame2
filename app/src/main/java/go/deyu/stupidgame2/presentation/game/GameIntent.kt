package go.deyu.stupidgame2.presentation.game

sealed class GameIntent {
    object FetchTheme : GameIntent()
    data class AskQuestion(val question: String) : GameIntent()
    object FetchClue : GameIntent()
    data class GuessMurderer(val murderer: String) : GameIntent()
}