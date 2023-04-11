package go.deyu.stupidgame2.presentation.game

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import go.deyu.stupidgame2.data.model.Suspect

@Composable
fun SuspectCard(suspect: Suspect, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize()
        ) {
            Text(
                text = "嫌疑人: ${suspect.name}",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "服裝: ${suspect.clothing}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "配件: ${suspect.accessories}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "不在場證明: ${suspect.alibi}",
                style = MaterialTheme.typography.body1
            )
            Text(
                text = "動機: ${suspect.motive}",
                style = MaterialTheme.typography.body1
            )
        }
    }
}
