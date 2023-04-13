package go.deyu.stupidgame2.data.model

data class GameData(
    val story: String,
    val suspects: List<Suspect>,
    val tell_user: String,
    val cubes: List<String>,
    val answer: Answer
)