package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch



@Composable
fun RotateCarImage(
    modifier: Modifier = Modifier,
    imagePath: String,
    selectedIndex: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }.build()
    }

    // image preload
    LaunchedEffect(imagePath) {
        for (imageNum in 1..60) {
            launch {
                imageLoader.enqueue(
                    ImageRequest.Builder(context)
                        .data(imagePath.imagePathToUrl(imageNum))
                        .build()
                )
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = imagePath.imagePathToUrl(selectedIndex + 1),
            contentDescription = "",
            imageLoader = imageLoader
        )
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