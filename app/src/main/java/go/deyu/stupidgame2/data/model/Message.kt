package go.deyu.stupidgame2.data.model

data class Message(
    val content: String,
    val role: String
)

object MessageBook {
    fun getNewGameMessage() = Message(
        content = "## 開局\n" +
                "\n" +
                "我們來玩一個遊戲，你來講個一個殺人案件的故事，讓我來猜猜看兇手是誰，嫌疑犯至少要三個，一開始讓我不要太清楚細節，隱藏兩個線索，讓我之後詢問你後得知。\n" +
                "遊戲資料資料請幫我用 Json 的方式呈現，資料結構請參考這個。\n" +
                "\n" +
                "```json\n" +
                "{\n" +
                "  \"story\": \"在一個晚上，一位名叫John的企業家被人發現倒在自家辦公室的地上，喉嚨被切斷了。現場留下了幾個線索，包括一把帶有指紋的刀、一件黑色外套和帽子、以及一把銀行家的鑰匙。\",\n" +
                "  \"suspects\": [\n" +
                "    {\n" +
                "      \"name\": \"受害人的前女友\",\n" +
                "      \"clothing\": \"\",\n" +
                "      \"accessories\": \"\",\n" +
                "      \"alibi\": \"\",\n" +
                "      \"motive\": \"情感因素\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"受害人的經紀人\",\n" +
                "      \"clothing\": \"\",\n" +
                "      \"accessories\": \"\",\n" +
                "      \"alibi\": \"\",\n" +
                "      \"motive\": \"財務利益\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"受害人的音樂競爭對手\",\n" +
                "      \"clothing\": \"\",\n" +
                "      \"accessories\": \"\",\n" +
                "      \"alibi\": \"\",\n" +
                "      \"motive\": \"競爭因素\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"cubes\": [\n" +
                "    \"警方在現場發現了一個帶有指紋的刀，但是這個刀不在嫌疑犯的資料中提到。\",\n" +
                "    \"根據調查，John和David之間存在財務糾紛。\"\n" +
                "  ],\n" +
                "  \"tell_user\": \"好了，現在你可以開始詢問我有關這三位嫌疑犯的問題，來看看我能否猜出兇手是誰。\",\n" +
                "  \"answer\": {\n" +
                "    \"suspect\": {\n" +
                "      \"name\": \"David\",\n" +
                "      \"clothing\": \"\",\n" +
                "      \"accessories\": \"銀行家的鑰匙\",\n" +
                "      \"alibi\": \"當晚在家中，沒有證人\",\n" +
                "      \"motive\": \"因為John拒絕了他的投資提案，引起財務糾紛\"\n" +
                "    },\n" +
                "    \"desc\":\"很好，你猜對了！女子的前男友是兇手。他的動機是因為女子和他分手了，他對她心有不甘。證據包括他穿著黑色外套、攜帶手機，以及他有著當晚在家中的證明。謝謝你玩這個遊戲，希望你玩得愉快！\"\n" +
                "  }\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "並且之後在我猜 嫌疑犯的 name 後告訴我是否猜測正確，讓我們開始吧！給我全新的故事！\n",
        role = "user"
    )

    fun getCubeMessage() = Message(
        content = "請給我一個線索",
        role = "user"
    )

    fun getGuessMessage(name: String) = Message(
        content = "我猜測兇手是 $name",
        role = "user"
    )
}