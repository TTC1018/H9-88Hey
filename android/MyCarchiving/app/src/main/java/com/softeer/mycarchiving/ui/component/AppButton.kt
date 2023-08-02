package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.bold18

@Composable
fun AppButton(
    modifier: Modifier,
    backgroundColor: Color,
    textColor: Color,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = textColor),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            style = bold18
        )
    }
}

@Preview
@Composable
fun PreviewAppButton() {
    val pressed: () -> Unit = {}
    AppButton(
        modifier = Modifier,
        backgroundColor = PrimaryBlue,
        textColor = HyundaiSand,
        text = "다음 단계로",
        onClick = pressed
    )
}