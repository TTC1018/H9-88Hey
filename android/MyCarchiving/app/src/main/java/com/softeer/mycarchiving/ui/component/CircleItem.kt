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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium12

@Composable
fun ProgressNumberCircle(
    modifier: Modifier = Modifier,
    numberText: String,
    color: Color,
    focus: Boolean = false
) {
    Box(
        modifier = modifier
            .width(20.dp)
            .height(20.dp)
            .background(
                color = color,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = numberText,
            color = White,
            style = medium12,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewProgressNumberCircle() {
    ProgressNumberCircle(
        modifier = Modifier,
        numberText = "01",
        color = PrimaryBlue,
        focus = false
    )
}