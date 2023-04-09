package go.deyu.stupidgame2.presentation.game

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import go.deyu.stupidgame2.domain.GameState

@Composable
fun GameContent(gameState: GameState) {
    Column {
        gameState.theme?.let { theme ->
            Text("Background: ${theme.background}")
            Text("Initial Information: ${theme.initialInfo}")

            theme.characters.forEach { character ->
                Text("Character: ${character.name}")
                Text("Description: ${character.description}")
            }
        }
    }
}