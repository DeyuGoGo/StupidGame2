package go.deyu.stupidgame2.data.api.response

import go.deyu.stupidgame2.data.model.Message

data class Choice(
    val finish_reason: String,
    val index: Int,
    val message: Message
)