package go.deyu.stupidgame2.presentation.game
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import go.deyu.stupidgame2.data.model.GameData
import go.deyu.stupidgame2.data.model.GuessResult

@Composable
fun GameOverScreen(gameViewModel: GameViewModel, guessResult: GuessResult) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "遊戲結束：${guessResult.message}",
            modifier = Modifier.align(Alignment.Center)
        )

        // Add more UI elements for the game over state (e.g., score, play again button, etc.)
    }
}