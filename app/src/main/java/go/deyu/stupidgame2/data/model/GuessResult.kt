package go.deyu.stupidgame2.data.model

@kotlinx.serialization.Serializable
data class GuessResult(val isCorrect: Boolean, val message: String)
