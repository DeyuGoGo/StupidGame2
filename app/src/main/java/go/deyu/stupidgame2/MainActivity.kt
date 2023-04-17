package go.deyu.stupidgame2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import go.deyu.stupidgame2.presentation.game.GameScreen
import go.deyu.stupidgame2.presentation.game.GameViewModel
import go.deyu.stupidgame2.presentation.theme.StupidGame2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val gameViewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StupidGame2Theme {
                LaunchedEffect(gameViewModel.shouldCloseApp) {
                    gameViewModel.shouldCloseApp.collect { shouldClose ->
                        if (shouldClose) {
                            finish()
                        }
                    }
                }
                GameScreen(gameViewModel = gameViewModel)
            }
        }
    }
}
