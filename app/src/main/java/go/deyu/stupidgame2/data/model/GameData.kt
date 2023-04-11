package go.deyu.stupidgame2.data.model

data class GameData(
    val story: String,
    val suspect: List<Suspect>,
    val tell_user: String
)