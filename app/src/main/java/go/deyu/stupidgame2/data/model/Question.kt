package go.deyu.stupidgame2.data.model

@kotlinx.serialization.Serializable
data class Question(
    val answer: String,
    val question: String
)