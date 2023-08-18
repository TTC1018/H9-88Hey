package com.softeer.mycarchiving.ui.component

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.util.scaleInAndOut
import kotlinx.coroutines.launch

private val TAG = "RotateCarImage"
private const val IMAGE_360_SIZE = 60

@Composable
fun RotateCarImage(
    modifier: Modifier = Modifier,
    imagePath: String,
    selectedIndex: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(1.0)
                    .build()
            }
            .build()
    }
    var loadCounter by remember { mutableIntStateOf(0) }
    val loadEnded by remember(loadCounter) { derivedStateOf { loadCounter != 0 && loadCounter == IMAGE_360_SIZE } }

    LaunchedEffect(imagePath) {
        loadCounter = 0
        // 다음 이미지 30개
        launch {
            for (imageNum in selectedIndex until selectedIndex + IMAGE_360_SIZE.div(2)) {
                launch {
                    val imageUrl = imagePath.imagePathToUrl((imageNum.mod(IMAGE_360_SIZE)) + 1)
                    imageLoader.execute(
                        ImageRequest.Builder(context)
                            .data(imageUrl)
                            .memoryCacheKey(imageUrl)
                            .listener { _, _ -> loadCounter++ }
                            .build()
                    )
                }
            }
        }

        // 이전 이미지 30개
        launch {
            for (imageNum in selectedIndex - 1 downTo selectedIndex - IMAGE_360_SIZE.div(2)) {
                launch {
                    val imageUrl = imagePath.imagePathToUrl((imageNum.mod(IMAGE_360_SIZE)) + 1)
                    imageLoader.execute(
                        ImageRequest.Builder(context)
                            .data(imageUrl)
                            .memoryCacheKey(imageUrl)
                            .listener { _, _ -> loadCounter++ }
                            .build()
                    )
                }
            }
        }
    }

    val colorStops = arrayOf(
        0.4f to White,
        1f to Color.Transparent
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-22).dp),
            painter = painterResource(id = R.drawable.ic_rotate_footer),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(50.dp)
                .background(
                    brush = Brush.radialGradient(colorStops = colorStops),
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "360",
                style = medium12,
                color = DarkGray,
            )
        }
        AnimatedContent(
            targetState = loadEnded,
            transitionSpec = { scaleInAndOut() },
            contentAlignment = Alignment.Center,
            label = ""
        ) {
            when (it) {
                true -> {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = imagePath.imagePathToUrl(selectedIndex + 1),
                        contentDescription = "",
                        imageLoader = imageLoader,
                        contentScale = ContentScale.Fit
                    )
                }

                false -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .aspectRatio(1f),
                        progress = loadCounter.toFloat() / IMAGE_360_SIZE,
                        color = PrimaryBlue,
                        trackColor = Color.Transparent,
                        strokeWidth = 10.dp
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        onLeftClick()
                    }
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        onRightClick()
                    }
            )
        }
    }
}

private fun String.imagePathToUrl(imageNum: Int) = this + imageNum.toString().padStart(3, '0') + ".png"

@Preview
@Composable
fun PreviewRotateCarImage() {
    val imageUrls = listOf("", "", "", "", "")
    var selectedIndex by remember { mutableStateOf(0) }

    RotateCarImage(
        imagePath = "",
        selectedIndex = selectedIndex,
        onLeftClick = {
            selectedIndex = (selectedIndex - 1) % imageUrls.size
        },
        onRightClick = {
            selectedIndex = (selectedIndex + 1) % imageUrls.size
        }
    )
}