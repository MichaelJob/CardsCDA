package ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import model.CardsModel

@Composable
@Preview
fun CardsUI(model: CardsModel) {

    with(model) {
        MaterialTheme {
            Column(
                Modifier.fillMaxSize().padding(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FlipCard(model)
                Button(onClick = {
                    val currentId = id
                    id = (currentId + 1) % data.size
                }) {
                    Text(next)
                }
            }
        }
    }
}

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun FlipCard(model: CardsModel) {
    //by https://stackoverflow.com/questions/68044576/how-to-make-flipcard-animation-in-jetpack-compose

    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateColor by animateColorAsState(
        targetValue = if (rotated) Color.LightGray else Color.hsl(207F, 1F, 0.86F),
        animationSpec = tween(500)
    )

    with(model) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Card(
                Modifier
                    .fillMaxSize(.5f)
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 8 * density
                    }
                    .clickable {
                        rotated = !rotated
                    },
                backgroundColor = animateColor
            )
            {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(text = if (rotated) data[id].answer else data[id].question,
                        modifier = Modifier
                            .graphicsLayer {
                                alpha = if (rotated) animateBack else animateFront
                                rotationY = rotation
                            })
                }
            }
        }
    }
}