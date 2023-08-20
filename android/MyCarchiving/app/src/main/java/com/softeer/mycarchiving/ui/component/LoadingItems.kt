package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.repeatCount
import com.softeer.mycarchiving.R

@Composable
fun MyArchiveLoadingScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components { add(ImageDecoderDecoder.Factory()) }
        .build()

    Box(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f / 1f),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = ImageRequest.Builder(context)
                .repeatCount(1)
                .data(R.drawable.img_loading_archive)
                .placeholder(R.drawable.img_loading_archive)
                .build(),
            contentDescription = "",
            imageLoader = imageLoader
        )
    }
}

@Preview
@Composable
fun PreviewCarLoadingIndicator() {
    MyArchiveLoadingScreen()
}