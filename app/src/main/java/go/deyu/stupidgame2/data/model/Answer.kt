package go.deyu.stupidgame2.data.model

@kotlinx.serialization.Serializable
data class Answer(
    val desc: String,
    val suspect: Suspect
)