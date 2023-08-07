package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.AlertPrimary
import com.softeer.mycarchiving.ui.theme.AlertSecondary
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiGold
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCornerMedium
import com.softeer.mycarchiving.ui.theme.roundCornerSmall

private val imageNames = listOf("컴포트 ||","컴포트 ||","컴포트 ||")

@Composable
fun MadeCarItem(
    modifier: Modifier,
    isTempSaved: Boolean,
    carName: String,
    madeDate: String,
    options: String,
    imageNames: List<String>,
    onItemClick: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(start = 22.dp, top = 20.dp, bottom = 20.dp)
            .clickable { onItemClick() }
    ) {
        if (isTempSaved) {
            Row(
                modifier = modifier.padding(end = 22.dp)
            ) {
                Text(
                    text = "*",
                    style = medium12,
                    color = AlertPrimary
                )
                Text(
                    text = stringResource(id = R.string.my_not_save_car),
                    style = medium12,
                    color = DarkGray
                )
            }
            Spacer(modifier = modifier.height(5.dp))
        }
        Row(
            modifier = modifier.padding(end = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.weight(1f),
                text = carName,
                style = bold18,
                overflow = TextOverflow.Ellipsis
            )
            MadeCarDateChip(modifier = modifier, madeDate = madeDate, isTempSaved = isTempSaved)
            Spacer(modifier = modifier.width(5.dp))
            XCircle(modifier = modifier, onClick = onDelete)
        }
        Spacer(modifier = modifier.height(5.dp))
        Text(
            text = options,
            style = regular14
        )
        Spacer(modifier = modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = imageNames,
                itemContent = {
                    MadeCarImageItem(modifier = modifier, name = it)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewMadeCarItem() {
    MadeCarItem(
        modifier = Modifier,
        isTempSaved = true,
        carName = "팰리세이드 Le Blanc",
        madeDate = "23년 7월 19일",
        options = "디젤 2.2 / 4WD / 7인승",
        imageNames = imageNames,
        onItemClick = {},
        onDelete = {}
    )
}

@Composable
fun MadeCarDateChip(
    modifier: Modifier,
    madeDate: String,
    isTempSaved: Boolean
) {
    Text(
        modifier = modifier
            .background(
                color = if (isTempSaved) AlertSecondary else HyundaiLightSand,
                shape = roundCornerMedium
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = if (isTempSaved) {
            stringResource(id = R.string.my_temp_save_date, madeDate)
        } else {
            stringResource(id = R.string.my_make_date, madeDate)
        },
        style = medium12,
        color = if (isTempSaved) AlertPrimary else HyundaiGold
    )
}

/*@Preview
@Composable
fun PreviewMadeCarDateChip() {
    MadeCarDateChip(
        modifier = Modifier,
        madeDate = "23년 7월 19일",
        isTempSaved = true
    )
}*/

@Composable
fun MadeCarImageItem(
    modifier: Modifier,
    name: String
) {
    Box(
        modifier = modifier
            .size(122.dp)
            .clip(roundCornerSmall)
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
        Box(
            modifier = modifier
                .align(Alignment.BottomStart)
                .padding(all = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = modifier
                    .background(color = Black, shape = roundCornerSmall)
                    .padding(all = 4.dp),
                text = name,
                style = regular12,
                color = HyundaiNeutral
            )
        }
    }
}

/*@Preview
@Composable
fun PreviewMadeCarImageItem() {
    MadeCarImageItem(
        modifier = Modifier,
        name = "컴포트 ||"
    )
}*/
