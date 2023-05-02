package go.deyu.stupidgame2.presentation.game

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import go.deyu.stupidgame2.presentation.theme.StupidGame2Theme
import go.deyu.stupidgame2.presentation.util.AdMobBanner
import go.deyu.stupidgame2.presentation.util.ProgressAnimation
import java.util.Queue

@Composable
fun GameScreen(gameViewModel: GameViewModel , modifier: Modifier = Modifier) {
    StupidGame2Theme {
        val gameState by gameViewModel.state.collectAsState()
        val gradient = Brush.radialGradient(
            colors = listOf(
                MaterialTheme.colors.primary,
                MaterialTheme.colors.secondary
            ),
            center = Offset(0f, 0f),
            radius = 800f
        )

        Box(modifier = modifier.background(gradient)) {
            AnimatedVisibility(
                visible = (!gameState.isLoading),
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it / 2 }),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(modifier = Modifier.weight(1f, true)) {
                        when (val screenState = gameState.screenState) {
                            is GameScreenState.Entrance -> GameEntranceScreen(gameViewModel)
                            is GameScreenState.InProgress -> InProgressGameScreen(
                                gameViewModel,
                                screenState.gameData
                            )
                            is GameScreenState.GameOver -> GameOverScreen(
                                gameViewModel,
                                screenState.guessResult
                            )

                        }
                    }
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                        AdMobBanner(adUnitId = "ca-app-pub-9829418644772076/4332365129")
                    }
                }
            }
            AnimatedVisibility(
                visible = (gameState.isLoading),
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
}