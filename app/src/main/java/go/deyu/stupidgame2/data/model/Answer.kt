package go.deyu.stupidgame2.data.model;

@kotlinx.serialization.Serializable
data class Answer(
    val full_story: String,
    val guess_fail: String,
    val guess_success: String,
    val suspect: Suspect
)
