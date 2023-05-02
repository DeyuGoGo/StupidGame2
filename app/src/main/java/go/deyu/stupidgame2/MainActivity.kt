package go.deyu.stupidgame2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import go.deyu.stupidgame2.domain.usecase.CreateGameWorker
import go.deyu.stupidgame2.presentation.game.GameScreen
import go.deyu.stupidgame2.presentation.game.GameViewModel
import go.deyu.stupidgame2.presentation.theme.StupidGame2Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

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
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                Logger.e("ID ${AdvertisingIdClient.getAdvertisingIdInfo(this@MainActivity).id}")

            }

        }
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
                        Button(onClick = { createGame() }) {
                            Text(text = "Create Game")
                        }
                    }
                    GameScreen(gameViewModel = gameViewModel, modifier = Modifier.weight(1f))
                }
            }
        }
        createGame()
    }

    private fun createGame() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<CreateGameWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
