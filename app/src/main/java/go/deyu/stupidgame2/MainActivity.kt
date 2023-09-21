package go.deyu.stupidgame2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.AndroidEntryPoint
import go.deyu.stupidgame2.domain.usecase.ClearGameWorker
import go.deyu.stupidgame2.domain.usecase.CreateGame10Worker
import go.deyu.stupidgame2.domain.usecase.TestWorker
import go.deyu.stupidgame2.presentation.game.GameScreen
import go.deyu.stupidgame2.presentation.game.GameViewModel
import go.deyu.stupidgame2.presentation.theme.StupidGame2Theme
import java.util.Collections

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val gameViewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)
//Optional if you want to add test device
        val configuration = RequestConfiguration.Builder()
            .setTestDeviceIds(Collections.singletonList("6858565a-24f8-48a2-bb59-b616652dc296"))
            .build()
        MobileAds.setRequestConfiguration(configuration)
        setContent {
            StupidGame2Theme {
                LaunchedEffect(gameViewModel.shouldCloseApp) {
                    gameViewModel.shouldCloseApp.collect { shouldClose ->
                        if (shouldClose) {
                            finish()
                        }
                    }
                }

                Column {
                    if (BuildConfig.DEBUG) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val interactionSource = remember { MutableInteractionSource() }
                            val isPressed by interactionSource.collectIsPressedAsState()
                            val interactionSource2 = remember { MutableInteractionSource() }
                            val isPressed2 by interactionSource.collectIsPressedAsState()
                            Button(
                                onClick = { testGo() },
                                modifier = Modifier
                                    .padding(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = animateColorAsState(
                                        if (isPressed) Color.Green else Color.White,
                                        label = ""
                                    ).value
                                ),
                                interactionSource = interactionSource
                            ) {
                                Text(text = "清除所有遊戲")
                            }

                            Button(
                                onClick = { createGame() },
                                modifier = Modifier
                                    .padding(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = animateColorAsState(
                                        if (isPressed2) Color.Green else Color.White,
                                        label = ""
                                    ).value
                                ),
                                interactionSource = interactionSource2
                            ) {
                                Text(text = "生成遊戲直到10個")
                            }
                        }
                    }


                    // 初始化一個用於旋轉的 Animatable
                    val rotation = remember { Animatable(0f) }

                    LaunchedEffect(key1 = true) {
                        // 先旋轉到 360 度
                        rotation.animateTo(
                            targetValue = 360f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                        // 再旋轉回 0 度
                        rotation.animateTo(
                            targetValue = 0f,
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                    }

                    GameScreen(
                        gameViewModel = gameViewModel,
                        modifier = Modifier
                            .weight(1f)
                            .graphicsLayer(rotationZ = rotation.value)
                    )
                }
            }
        }
    }

    private fun clearGames() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ClearGameWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }


    private fun createGame() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<CreateGame10Worker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "CreateGame10WorkerUniqueName",  // 這裡需要一個獨特的名稱
            ExistingWorkPolicy.KEEP,         // 如果已有相同名稱的工作，則保留已有的，不排入新的請求
            workRequest
        )
    }

    private fun testGo(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<TestWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

}
