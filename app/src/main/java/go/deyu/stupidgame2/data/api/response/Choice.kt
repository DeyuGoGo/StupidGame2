package go.deyu.stupidgame2.data.api.response

data class Choice(
    val finish_reason: String,
    val index: Int,
    val message: Message
)