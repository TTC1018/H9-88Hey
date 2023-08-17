package com.softeer.mycarchiving.ui.loading

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.makingcar.selectmodel.SelectModelViewModel

@Composable
internal fun LoadingRoute(
    modifier: Modifier = Modifier,
    onLoading: () -> Unit,
    selectModelViewModel: SelectModelViewModel = hiltViewModel(),
) {
    LoadingScreen(
        modifier = modifier,
        onLoading = onLoading,
    )
}

@Composable
internal fun LoadingScreen(
    modifier: Modifier = Modifier,
    onLoading: () -> Unit,
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                add(ImageDecoderDecoder.Factory())
            }.build()
    }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Image(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onLoading() },
            painter = rememberAsyncImagePainter(
                model = R.drawable.img_loading,
                imageLoader = imageLoader
            ),
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun PreviewLoadingScreen() {
    LoadingScreen(
        onLoading = {}
    )
}