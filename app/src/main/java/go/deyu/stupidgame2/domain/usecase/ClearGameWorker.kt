package go.deyu.stupidgame2.domain.usecase

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.tasks.await

@HiltWorker
class ClearGameWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
) : CoroutineWorker(context, params) {
    private val db = Firebase.firestore

    override suspend fun doWork(): Result {
        val collectionRef = db.collection("stories_v2")
        collectionRef.get().await().documents.forEach {
            db.collection("stories_v2").document(it.id).delete().await()
        }
        return Result.success()
    }
}
