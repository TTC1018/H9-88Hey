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


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RotateCarImage(
    modifier: Modifier = Modifier,
    imageUrls: List<String>,
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
    LaunchedEffect(imageUrls) {
        for (imageUrl in imageUrls) {
            launch {
                imageLoader.enqueue(
                    ImageRequest.Builder(context)
                        .data(imageUrl)
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
            model = imageUrls.getOrNull(selectedIndex),
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

@Preview
@Composable
fun PreviewRotateCarImage() {
    val imageUrls = listOf("", "", "", "", "")
    var selectedIndex by remember { mutableStateOf(0) }

    RotateCarImage(
        imageUrls = listOf(""),
        selectedIndex = selectedIndex,
        onLeftClick = {
            if (selectedIndex == 0)
                selectedIndex = imageUrls.size - 1
            else
                selectedIndex--
        },
        onRightClick = {
            if (selectedIndex == imageUrls.size - 1)
                selectedIndex = 0
            else
                selectedIndex++
        }
    )
}