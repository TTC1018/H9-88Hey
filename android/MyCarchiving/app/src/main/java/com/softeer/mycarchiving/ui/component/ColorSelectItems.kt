package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.imageLoader
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiActiveBlue
import com.softeer.mycarchiving.ui.theme.roundCorner

@Composable
fun CarImageSelectItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    isSelect: Boolean,
    imageUrl: String,
    imageCache: ImageLoader = LocalContext.current.imageLoader,
) {
    Surface(
        modifier = modifier
            .size(80.dp)
            .clickable {
                onItemClick()
            },
        shape = roundCorner,
        border = if (isSelect) BorderStroke(width = 2.dp, color = HyundaiActiveBlue) else null
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop     ,
            imageLoader = imageCache
        )
    }
}

@Composable
fun CarColorSelectItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
    imageUrl: String,
    selected: Boolean,
) {
    Box(
        modifier = modifier
            .size(59.dp)
            .clickable {
                onItemClick()
            }
    ){
        Surface(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center),
            shape = roundCorner,
            border = if (selected) BorderStroke(width = 3.dp, color = HyundaiActiveBlue) else null
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize(),
                imageModel = { imageUrl },
                previewPlaceholder = R.drawable.ic_launcher_background,
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop
                ),
            )
        }
        if (selected) {
            CheckCircle(
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
        }
    }
}

@Preview
@Composable
fun PreviewCarImageSelectItem() {
    CarImageSelectItem(
        modifier = Modifier,
        onItemClick = {},
        isSelect = true,
        imageUrl = ""
    )
}


@Preview
@Composable
fun PreviewCarColorSelectItem() {
    CarColorSelectItem(
        modifier = Modifier,
        onItemClick = {},
        imageUrl = "",
        selected = false,
    )
}