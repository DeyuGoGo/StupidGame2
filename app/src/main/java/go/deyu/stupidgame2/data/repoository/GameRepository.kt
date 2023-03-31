package go.deyu.stupidgame2.data.repoository

import go.deyu.stupidgame2.data.api.GameApi
import go.deyu.stupidgame2.data.model.Answer
import go.deyu.stupidgame2.data.model.Clue
import go.deyu.stupidgame2.data.model.GuessResult
import go.deyu.stupidgame2.data.model.Theme
import retrofit2.Response

class GameRepository(private val apiService: GameApi) {

    suspend fun fetchTheme(): Result<Theme> {
        val response = apiService.fetchTheme()
        return handleResponse(response)
    }

    suspend fun askQuestion(question: String): Result<Answer> {
        val response = apiService.askQuestion(question)
        return handleResponse(response)
    }

    suspend fun fetchClue(): Result<Clue> {
        val response = apiService.fetchClue()
        return handleResponse(response)
    }

    suspend fun guessMurderer(murderer: String): Result<GuessResult> {
        val response = apiService.guessMurderer(murderer)
        return handleResponse(response)
    }

    private fun <T> handleResponse(response: Response<T>): Result<T> {
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            // 这里可以自定义您的错误处理逻辑，例如解析错误信息
            Result.failure(RuntimeException("API request failed with error code: ${response.code()}"))
        }
    }
}