package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import androidx.compose.ui.res.painterResource
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiActiveBlue
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold12
import com.softeer.mycarchiving.ui.theme.medium12

@Composable
fun ProgressNumberCircle(
    modifier: Modifier = Modifier,
    numberText: String,
    color: Color,
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

@Composable
fun CheckCircle(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .size(22.dp)
            .background(
                color = HyundaiActiveBlue,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            tint = HyundaiNeutral
        )
    }
}

@Composable
fun XCircle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(20.dp)
            .border(width = 1.dp, color = MediumGray, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(15.dp),
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null,
            tint = MediumGray
        )
    }
}

@Composable
fun SelectFillNumberCircle(
    modifier: Modifier = Modifier,
    numberText: String,
    isFill: Boolean = false
) {
    Box(
        modifier = modifier
            .size(22.dp)
            .background(
                color = if (isFill) PrimaryBlue else HyundaiNeutral,
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = if (isFill) HyundaiNeutral else PrimaryBlue,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = numberText,
            color = if (isFill) HyundaiLightSand else PrimaryBlue,
            style = bold12,
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
    )
}

@Preview
@Composable
fun PreviewCheckCircle() {
    CheckCircle(modifier = Modifier)
}

@Preview
@Composable
fun PreviewXCircle() {
    XCircle(modifier = Modifier, onClick = {})
}

@Preview
@Composable
fun PreviewSelectFillNumberCircle() {
    SelectFillNumberCircle(numberText = "01", isFill = true)
}