package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.MediumDarkGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.ui.theme.roundCornerMedium
import com.softeer.mycarchiving.ui.theme.roundCornerMinimum
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
            MyArchiveDateChip(
                modifier = modifier,
                chipText = if (isTempSaved) {
                    stringResource(id = R.string.my_temp_save_date, madeDate)
                } else {
                    stringResource(id = R.string.my_temp_save_date, madeDate)
                },
                isTempSaved = isTempSaved
            )
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

@Composable
fun MyArchiveDateChip(
    modifier: Modifier,
    chipText: String,
    isTempSaved: Boolean
) {
    Text(
        modifier = modifier
            .background(
                color = if (isTempSaved) AlertSecondary else HyundaiLightSand,
                shape = roundCornerMedium
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = chipText,
        style = medium12,
        color = if (isTempSaved) AlertPrimary else HyundaiGold
    )
}

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

@Composable
fun SavedCarItem(
    modifier: Modifier,
    carName: String,
    madeDate: String,
    options: String,
    outerColor: String,
    innerColor: String,
    selectedOptions: List<String>,
    review: String,
    onItemClick: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp)
            .background(color = White, shape = roundCorner)
            .border(width = 1.dp, color = HyundaiSand)
            .clickable { onItemClick() }
            .padding(horizontal = 18.dp, vertical = 21.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = modifier.weight(1f),
                text = carName,
                style = bold18,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.width(10.dp))
            XCircle(modifier = modifier, onClick = onDelete)
        }
        Spacer(modifier = modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = modifier.weight(1f),
                text = options,
                style = regular14,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.width(4.dp))
            MyArchiveDateChip(
                modifier = modifier,
                chipText = stringResource(id = R.string.archive_test_drive_date, madeDate),
                isTempSaved = false
            )
        }
        Spacer(modifier = modifier.height(13.dp))
        Row {
            Text(
                text = stringResource(id = R.string.summary_outer),
                style = medium14
            )
            Spacer(modifier = modifier.width(12.dp))
            Text(
                modifier = modifier.weight(1f),
                text = outerColor,
                style = regular14,
                color = MediumDarkGray
            )
            Text(
                text = stringResource(id = R.string.summary_inner),
                style = medium14
            )
            Spacer(modifier = modifier.width(12.dp))
            Text(
                modifier = modifier.weight(1f),
                text = innerColor,
                style = regular14,
                color = MediumDarkGray
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.selected_option),
                style = medium14
            )
            Spacer(modifier = modifier.width(7.dp))
            SavedCarOptionChip(modifier = modifier, name = selectedOptions[0])
            Spacer(modifier = modifier.width(4.dp))
            if (selectedOptions.size > 1) {
                SavedCarOptionChip(modifier = modifier, name = selectedOptions[1])
                Spacer(modifier = modifier.width(4.dp))
            }
            if (selectedOptions.size > 2) {
                Text(
                    text = "+${selectedOptions.size - 2}",
                    style = medium12
                )
            }
        }
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(color = HyundaiLightSand, shape = roundCorner)
                .height(108.dp)
                .padding(horizontal = 15.dp, vertical = 12.dp),
            text = review,
            style = regular14,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun SavedCarOptionChip(
    modifier: Modifier,
    name: String
) {
    Text(
        modifier = modifier
            .background(color = HyundaiSand, shape = roundCornerMinimum)
            .padding(horizontal = 10.dp, vertical = 3.dp),
        text = name,
        style = regular14
    )
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

@Preview
@Composable
fun PreviewSavedCarItem() {
    SavedCarItem(
        modifier = Modifier,
        carName = "펠리세이드 Le Blanc ",
        madeDate = "23년 7월 19일",
        options = "디젤 2.2 / 4WD / 7인승",
        outerColor = "문라이트 블루펄",
        innerColor = "퀼팅 천연(블랙)",
        selectedOptions = imageNames,
        review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
        onItemClick = {},
        onDelete = {}
    )
}