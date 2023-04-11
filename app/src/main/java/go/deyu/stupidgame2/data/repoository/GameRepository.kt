package go.deyu.stupidgame2.data.repoository

import com.orhanobut.logger.Logger
import go.deyu.stupidgame2.data.api.ChatApi
import go.deyu.stupidgame2.data.api.request.ChatRequest
import go.deyu.stupidgame2.data.api.request.Message
import go.deyu.stupidgame2.data.api.response.ChatResponse
import go.deyu.stupidgame2.data.model.Clue
import go.deyu.stupidgame2.data.model.GuessResult
import go.deyu.stupidgame2.data.model.Theme
import retrofit2.Response

class GameRepository(private val apiService: ChatApi) {

    suspend fun fetchTheme(): Result<Theme> {
        val response = apiService.fetchTheme()
        return handleResponse(response)
    }
    suspend fun requestNewGameData(): Result<ChatResponse> {
        val response = apiService.fetchChat(ChatRequest(model = "gpt-3.5-turbo",messages = listOf(Message("## 開局\n" +
                "\n" +
                "我們來玩一個遊戲，你來講個一個殺人案件的故事，讓我來猜猜看兇手是誰，嫌疑犯至少要三個，一開始讓我不要太清楚細節，隱藏兩個線索，讓我之後詢問你後得知。\n" +
                "遊戲資料資料請幫我用 Json 的方式呈現，資料結構請參考這個。\n" +
                "\n" +
                "```json\n" +
                "{\n" +
                "  \"story\":  \"在一個晚上，一位名叫John的企業家被人發現倒在自家辦公室的地上，喉嚨被切斷了。現場留下了幾個線索，包括一把帶有指紋的刀、一件黑色外套和帽子、以及一把銀行家的鑰匙。\",\n" +
                "  \"suspect\": [\n" +
                "    {\n" +
                "      \"name\": \"unknown\",\n" +
                "      \"clothing\": \"黑色外套和帽子\",\n" +
                "      \"accessories\": \"\",\n" +
                "      \"alibi\": \"\",\n" +
                "      \"motive\": \"未知\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"受害人的生意夥伴\",\n" +
                "      \"clothing\": \"\",\n" +
                "      \"accessories\": \"銀行家的鑰匙\",\n" +
                "      \"alibi\": \"\",\n" +
                "      \"motive\": \"可能涉及財務糾紛\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tell_user\" : \"好了，現在你可以開始詢問我有關這三位嫌疑犯的問題，來看看我能否猜出兇手是誰。\"\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "並且之後在我猜 嫌疑犯的 name 後告訴我是否猜測正確，讓我們開始吧！給我全新的故事！", "user"))))
        Logger.e("response = $response")
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
            Result.failure(RuntimeException("API request failed with error code: ${response.code()}"))
        }
    }
}