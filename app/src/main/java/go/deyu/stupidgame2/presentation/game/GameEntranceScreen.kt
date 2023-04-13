package go.deyu.stupidgame2.presentation.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import go.deyu.stupidgame2.R

@Composable
fun GameEntranceScreen(gameViewModel: GameViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.artwork),
            contentDescription = null
        )
        Button(modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),

            onClick = {
                gameViewModel.sendIntent(GameIntent.NewGame)
            }) {
            Image(painter = painterResource(id = R.drawable.startbutton), contentDescription = null)
        }

    }
}