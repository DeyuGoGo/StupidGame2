package go.deyu.stupidgame2.presentation.game

import go.deyu.stupidgame2.data.model.Suspect

sealed class GameIntent {
    object GoHome : GameIntent()
    object NewGame : GameIntent()
    class GuessSuspect(val suspect: Suspect) : GameIntent()
    object ShowNextClue : GameIntent()
    object FinishGame : GameIntent()
}