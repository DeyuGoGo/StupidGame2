package go.deyu.stupidgame2.data.model

@kotlinx.serialization.Serializable
data class GameData(
    val story: String,
    val suspects: List<Suspect>,
    val tell_user: String,
    val cubes: List<String>,
    val showCube: Int = 0,
    val answer: Answer
)