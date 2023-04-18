package go.deyu.stupidgame2.data.api

import go.deyu.stupidgame2.data.api.request.ChatRequest
import go.deyu.stupidgame2.data.api.request.ImageRequest
import go.deyu.stupidgame2.data.api.response.ChatResponse
import go.deyu.stupidgame2.data.model.Answer
import go.deyu.stupidgame2.data.model.Clue
import go.deyu.stupidgame2.data.model.GuessResult
import go.deyu.stupidgame2.data.model.Theme
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatApi {
    @POST("v1/chat/completions")
    suspend fun fetchChat(@Body chatRequest: ChatRequest): Response<ChatResponse>

    @POST("v1/images/generations")
    suspend fun fetchImage(@Body imageRequest: ImageRequest): Response<Any>
}