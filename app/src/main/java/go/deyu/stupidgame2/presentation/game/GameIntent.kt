package go.deyu.stupidgame2.presentation.game

sealed class GameIntent {
    object NewGame : GameIntent()
}