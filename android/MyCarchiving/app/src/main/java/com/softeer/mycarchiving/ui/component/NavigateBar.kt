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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.medium10
import com.softeer.mycarchiving.ui.theme.medium14

@Composable
fun NavigateBar(
    modifier: Modifier,
    title: String,
    onBackPressed: () -> Unit,
    onTailPressed: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = HyundaiSand)
            .padding(end = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackPressed) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        }
        Text(
            text = title,
            style = medium14
        )
        Column(
            modifier = modifier.clickable { onTailPressed() },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_archive),
                contentDescription = null
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                text = stringResource(id = R.string.archive),
                style = medium10,
            )
        }
    }
}

@Preview
@Composable
fun PreviewNavigateBar() {
    val pressed: () -> Unit = {}
    NavigateBar(modifier = Modifier, title = "펠리세이드", onBackPressed = pressed, onTailPressed = pressed)
}