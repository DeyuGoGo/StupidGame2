package go.deyu.stupidgame2.presentation.game

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import go.deyu.stupidgame2.presentation.util.ProgressAnimation

@Composable
fun GameScreen(gameViewModel: GameViewModel) {
    val gameState by gameViewModel.state.collectAsState()
    val gameData = gameState.gameData
    Box {
        AnimatedVisibility(
            visible = (!gameState.isLoading ),
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it / 2 }),
        ) {
            LazyColumn {
                item {
                    Button(onClick = {
                        gameViewModel.sendIntent(GameIntent.NewGame)
                    }) {
                        Text("New Game")
                    }
                }

                if (gameData != null) {
                    item {
                        Text(text = "故事：${gameData.story}")
                    }
                    items(gameData.suspect) { s ->
                        SuspectCard(suspect = s) {

                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = (gameState.isLoading ),
            enter = fadeIn() + slideInHorizontally(),
            exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it / 2 }),
        ) {
            ProgressAnimation(
                modifier = Modifier.fillMaxSize(),
                color = Color.Red
            )
        }
    }
}