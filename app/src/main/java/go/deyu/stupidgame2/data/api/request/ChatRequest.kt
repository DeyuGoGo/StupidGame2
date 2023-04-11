package go.deyu.stupidgame2.data.api.request

data class ChatRequest(
    val messages: List<Message>,
    val model: String
)