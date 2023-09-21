package go.deyu.stupidgame2.domain.usecase

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.orhanobut.logger.Logger
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import go.deyu.stupidgame2.data.model.MessageBook
import kotlin.math.max

@HiltWorker
class TestWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val requestChatUseCase: RequestChatUseCase
) : CoroutineWorker(context, params) {
    private val db = Firebase.firestore

    override suspend fun doWork(): Result {
        val startMessage = MessageBook.getTestMessage()
        val result = requestChatUseCase(listOf(startMessage))
        Logger.d("result = $result")
        return Result.success()
    }


    companion object {
        private const val TAG = "TestWorker"
    }
}

class Solution {
    fun majorityElement(nums: IntArray): Int {
        val map = mutableMapOf<Int, Int>()
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }
        return map.maxBy { it.value }!!.key
    }
}