package go.deyu.stupidgame2.data.model

@kotlinx.serialization.Serializable
data class Suspect(
    val accessories: String,
    val alibi: String,
    val clothing: String,
    val motive: String,
    val name: String,
    val questions: List<Question>,
)