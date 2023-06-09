package go.deyu.stupidgame2.domain.usecase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import go.deyu.stupidgame2.data.model.GameData
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RequestNewGameUseCase() {
    suspend operator fun invoke(): GameData? {
        val  db = Firebase.firestore
        val doc = db.collection("stories_v2").get().await().documents.random()
        val story = doc.data
        val jsonString = Gson().toJson(story)
        return try {
            Json.decodeFromString<GameData>(jsonString)
        } catch (e: Exception) {
            null
        }
    }
}