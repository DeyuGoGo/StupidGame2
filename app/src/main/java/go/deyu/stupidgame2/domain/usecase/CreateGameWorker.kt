package go.deyu.stupidgame2.domain.usecase

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import go.deyu.stupidgame2.data.model.GameData
import go.deyu.stupidgame2.data.model.MessageBook
import kotlinx.coroutines.tasks.await

@HiltWorker
class CreateGameWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val requestChatUseCase: RequestChatUseCase
) : CoroutineWorker(context, params) {
    private val db = Firebase.firestore

    override suspend fun doWork(): Result {
        return try {
            val collectionRef = db.collection("stories")
            val gameData = requestNewGameData() ?: return Result.failure()
            val result = collectionRef.add(gameData).await()
            Logger.d(TAG, "DocumentSnapshot added with ID: ${result.id}")
            Result.success()
        } catch (e: Exception) {
            Logger.e(TAG, "Error adding document", e)
            Result.failure()
        }
    }

    private suspend fun requestNewGameData(): GameData? {
        val startMessage = MessageBook.getNewGameMessage()
        val result = requestChatUseCase(listOf(startMessage))
        return try {
            val newGameMessage = result.getOrNull()?.choices?.last()?.message
            val gameData = newGameMessage!!.content.let {
                Gson().fromJson(it, GameData::class.java)
            }
            gameData
        } catch (e: Exception) {
            Logger.e("e = $e")
            null
        }
    }

    companion object {
        private const val TAG = "AddDataWorker"
    }
}
