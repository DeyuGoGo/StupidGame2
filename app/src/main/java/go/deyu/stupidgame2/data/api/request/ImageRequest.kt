package go.deyu.stupidgame2.data.api.request

data class ImageRequest(
    val prompt: String,
    val n: Int = 1,
    val size: String = "256x256"
)