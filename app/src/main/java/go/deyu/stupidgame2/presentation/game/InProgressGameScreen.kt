package go.deyu.stupidgame2.presentation.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import go.deyu.stupidgame2.data.model.GameData
import go.deyu.stupidgame2.presentation.theme.StupidGame2Theme

@Composable
fun InProgressGameScreen(gameViewModel: GameViewModel, gameData: GameData) {
    StupidGame2Theme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("遊戲進行中") },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    item {
                        Column {
                            Button(
                                onClick = { gameViewModel.sendIntent(GameIntent.ShowNextClue) },
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                            ) {
                                Text("顯示提示線索")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    items(gameData.cubes) { c ->
                        if (gameData.cubes.indexOf(c) < gameData.showCube) {
                            Text(
                                text = "線索：${c}",
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onBackground,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "故事：${gameData.story}",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }

                    items(gameData.suspects) { s ->
                        Spacer(modifier = Modifier.height(8.dp))
                        SuspectCard(
                            suspect = s,
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(2.dp, MaterialTheme.shapes.medium),
                            onClick = {
                                gameViewModel.sendIntent(GameIntent.GuessSuspect(suspect = s))
                            }
                        )
                    }
                }
            }
        )
    }
}