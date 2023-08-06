package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R

@Composable
fun RotateCarImage(
    modifier: Modifier,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = modifier.fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { onLeftClick() }
            )
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { onRightClick() }
            )
        }
    }
}

@Preview
@Composable
fun PreviewRotateCarImage() {
    RotateCarImage(modifier = Modifier, {}, {})
}