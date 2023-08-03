package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium12

@Composable
fun ProgressNumberCircle(
    modifier: Modifier,
    number: Int,
    focus: Boolean
) {
    Box(
        modifier = modifier
        .width(20.dp)
        .height(20.dp)
        .background(
            color = if (focus) Black else MediumGray,
            shape = CircleShape
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.toString(),
            color = White,
            style = medium12,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewProgressNumberCircle() {
    ProgressNumberCircle(modifier = Modifier, number = 1, focus = false)
}