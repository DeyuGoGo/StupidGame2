package go.deyu.stupidgame2.data.api

import go.deyu.stupidgame2.data.api.request.ChatRequest
import go.deyu.stupidgame2.data.api.request.ImageRequest
import go.deyu.stupidgame2.data.api.response.ChatResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ChatApi {
    @POST("v1/chat/completions")
    suspend fun fetchChat(@Body chatRequest: ChatRequest): Response<ChatResponse>

    @POST("v1/images/generations")
    suspend fun fetchImage(@Body imageRequest: ImageRequest): Response<Any>
}