package go.deyu.stupidgame2.presentation.game

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import go.deyu.stupidgame2.data.model.Suspect

@Composable
fun SuspectCard(suspect: Suspect, onClick: () -> Unit, modifier: Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

    if (showDialog) {
        Dialog(

            onDismissRequest = { showDialog = false }) {


            Surface(shape = MaterialTheme.shapes.medium, elevation = 8.dp) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .width(300.dp)
                    ) {
                        Text(
                            text = "嫌疑人: ${suspect.name}",
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn {
                            itemsIndexed(suspect.questions) { index, question ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedIndex = index
                                        }
                                ) {
                                    RadioButton(
                                        selected = selectedIndex == index,
                                        onClick = { selectedIndex = index }
                                    )
                                    Text(
                                        text = question.question,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        if (selectedIndex != -1) {
                            Text(text = "答案：${suspect.questions[selectedIndex].answer}")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Button(
                                onClick = {
                                    onClick()
                                    showDialog = false
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("猜他是兇手")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(
                                onClick = { showDialog = false },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("取消")
                            }
                        }
                    }
                }

            }
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { showDialog = true },
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