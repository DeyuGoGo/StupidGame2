package go.deyu.stupidgame2.data.repoository

import go.deyu.stupidgame2.data.api.ChatApi
import go.deyu.stupidgame2.data.api.request.ChatRequest
import go.deyu.stupidgame2.data.api.request.ImageRequest
import go.deyu.stupidgame2.data.model.Message
import go.deyu.stupidgame2.data.api.response.ChatResponse
import retrofit2.Response

class GameRepository(private val apiService: ChatApi) {

    suspend fun requestImage(prompt :String): Result<Any> {
        val response = apiService.fetchImage(ImageRequest(prompt = "大奶美女"))
        return handleResponse(response)
    }

    suspend fun requestChat(messages: List<Message>): Result<ChatResponse> {
        val response = apiService.fetchChat(ChatRequest(model = "gpt-3.5-turbo", messages = messages))
        return handleResponse(response)
    }

    private fun <T> handleResponse(response: Response<T>): Result<T> {
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(RuntimeException("API request failed with error code: ${response.code()}"))
        }
    }
}