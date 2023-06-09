package go.deyu.stupidgame2.presentation.util
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview
@Composable
fun PreviewWaProgressAnimation() {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        ProgressAnimation()
    }
}

@Composable
fun ProgressAnimation(modifier: Modifier = Modifier , color: Color = MaterialTheme.colors.primary) {

    val dots = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )

    dots.forEachIndexed { index, animatable ->
        LaunchedEffect(animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f, animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 2000
                        0.0f at 0 with LinearOutSlowInEasing // for 0-15 ms
                        1.0f at 200 with LinearOutSlowInEasing // for 15-75 ms
                        0.0f at 400 with LinearOutSlowInEasing // for 0-15 ms
                        0.0f at 2000
                    },
                    repeatMode = RepeatMode.Restart,
                )
            )
        }
    }

    val dys = dots.map { it.value }

    val travelDistance = with(LocalDensity.current) { 15.dp.toPx() }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        dys.forEachIndexed { index, dy ->
            Box(
                Modifier
                    .size(15.dp)
                    .graphicsLayer {
                        translationY = -dy * travelDistance
                    } .background(color = color, shape = CircleShape)
            )
            if (index != dys.size - 1) {
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}