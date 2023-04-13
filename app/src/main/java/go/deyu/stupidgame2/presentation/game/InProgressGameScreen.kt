package go.deyu.stupidgame2.presentation.game

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import go.deyu.stupidgame2.data.model.GameData

@Composable
fun InProgressGameScreen(gameViewModel: GameViewModel, gameData: GameData) {
    LazyColumn {
        item {
            Text(text = "故事：${gameData.story}")
        }

        items(gameData.suspects) { s ->
            SuspectCard(suspect = s) {
                gameViewModel.sendIntent(GameIntent.GuessSuspect(suspect = s))
            }
        }

        // Add more UI elements for the game progress (e.g., clues, questions, etc.)
    }
}
