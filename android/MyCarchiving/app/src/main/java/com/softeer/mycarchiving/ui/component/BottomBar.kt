package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12

@Composable
fun BottomBar(
    modifier: Modifier,
    totalPrice: String,
    onShowSummary: () -> Unit,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(137.dp)
            .background(HyundaiSand)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 32.dp
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.clickable { onShowSummary() }
            ) {
                Text(
                    text = stringResource(id = R.string.show_summary),
                    style = medium12,
                )
                Spacer(modifier = modifier.height(5.dp))
                Divider(
                    modifier = modifier.width(61.dp),
                    thickness = 2.dp,
                    color = Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = totalPrice,
                    style = bold18
                )
                Spacer(modifier = modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.bottom_bar_price),
                    style = medium12
                )
            }
        }
        Spacer(modifier = modifier.height(13.dp))
        HyundaiButton(
            modifier = modifier,
            backgroundColor = PrimaryBlue,
            textColor = HyundaiSand,
            text = stringResource(id = R.string.bottom_bar_next_step),
            onClick = onButtonClick
        )
    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    val pressed: () -> Unit = {}
    BottomBar(
        modifier = Modifier,
        totalPrice = "47,720,000",
        onShowSummary = pressed,
        onButtonClick = pressed
    )
}

