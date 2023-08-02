package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiNavy
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.roundCorner

@Composable
fun HyundaiButton(
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
        shape = roundCorner
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
    HyundaiButton(
        modifier = Modifier,
        backgroundColor = PrimaryBlue,
        textColor = HyundaiSand,
        text = "다음 단계로",
        onClick = pressed
    )
}

@Composable
fun OptionAddButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    var isSelect by remember { mutableStateOf(false) }
    Button(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = if (isSelect) {
            ButtonDefaults.buttonColors(containerColor = HyundaiNavy, contentColor = HyundaiNeutral)
        } else {
            ButtonDefaults.buttonColors(containerColor = HyundaiNeutral, contentColor = HyundaiNavy)
        },
        border = if (isSelect) null else BorderStroke(width = 1.dp, color = HyundaiNavy),
        onClick = {
            isSelect = !isSelect
            onClick()
        },
        shape = roundCorner,
    ) {
        Text(
            text = if (isSelect) stringResource(id = R.string.make_car_add_done) else stringResource(id = R.string.make_car_add),
            style = medium14,
            color = if (isSelect) HyundaiNeutral else HyundaiNavy
        )
        if (isSelect) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun PreviewOptionAddButton() {
    OptionAddButton(modifier = Modifier, onClick = {})
}


