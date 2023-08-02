package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue500

@Composable
fun CarLoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(140.dp)
            .aspectRatio(1f / 1f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize(),
            color = PrimaryBlue500,
            trackColor = HyundaiLightSand,
            strokeWidth = 8.dp
        )
        Image(
            modifier = Modifier
                .width(80.dp)
                .height(34.dp),
            painter = painterResource(id = R.drawable.ic_loading_car),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PreviewCarLoadingIndicator() {
    CarLoadingIndicator()
}