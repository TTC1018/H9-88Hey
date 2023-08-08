package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.medium20
import com.softeer.mycarchiving.ui.theme.regular14

@Composable
fun OptionHeadText(
    modifier: Modifier = Modifier,
    optionName: String
) {
    Text(
        modifier = modifier,
        style = medium20,
        text = optionName
    )
}

@Composable
fun OptionHeadComment(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        style = regular14,
        text = stringResource(id = R.string.make_car_comment_announce)
    )
}

@Composable
fun OptionInfoDivider(
    modifier: Modifier = Modifier,
    thickness: Dp,
    color: Color
) {
    Divider(
        modifier = modifier
            .fillMaxWidth(),
        thickness = thickness,
        color = color
    )
}

@Preview
@Composable
fun PreviewOptionHeadText() {
    OptionHeadText(optionName = "문라이트 블루펄")
}

@Preview
@Composable
fun PreviewOptionHeadComment() {
    OptionHeadComment()
}

@Preview
@Composable
fun PreviewInfoDivider() {
    OptionInfoDivider(thickness = 4.dp, color = DarkGray)
}