package go.deyu.stupidgame2.data.api.request

import go.deyu.stupidgame2.data.model.Message

data class ChatRequest(
    val messages: List<Message>,
    val model: String
)