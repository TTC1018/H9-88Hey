package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.CarFeedType
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedSelectedOptionUiModel
import com.softeer.mycarchiving.ui.theme.AlertPrimary
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.MediumDarkGray
import com.softeer.mycarchiving.ui.theme.ThinGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular10
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.ui.theme.roundCornerSmall
import com.softeer.mycarchiving.util.toDateString


@Composable
fun MadeCarFeed(
    modifier: Modifier = Modifier,
    isTempSaved: Boolean,
    madeDate: String,
    modelName: String,
    trimName: String?,
    trimOptions: String,
    selectedOptions: List<ArchiveFeedSelectedOptionUiModel>,
    onFeedClick: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(start = 22.dp, top = 20.dp, bottom = 20.dp)
            .clickable { onFeedClick() }
    ) {
        if (isTempSaved) {
            Row(
                modifier = Modifier.padding(end = 22.dp)
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
            Spacer(modifier = Modifier.height(5.dp))
        }
        Row(
            modifier = Modifier
                .padding(end = 22.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$modelName $trimName",
                style = bold18,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                CarFeedDateChip(
                    modifier = Modifier,
                    date = madeDate.toDateString(),
                    feedType = if (isTempSaved) CarFeedType.TEMP_SAVE else CarFeedType.MADE
                )
                XCircle(
                    modifier = Modifier,
                    onClick = onDelete
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = trimOptions.ifEmpty { stringResource(id = R.string.my_make_car_trim_none) },
            style = regular14
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            modifier = Modifier.padding(end = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            if (selectedOptions.isNotEmpty()) {
                items(
                    items = selectedOptions,
                    itemContent = {
                        MadeCarImageItem(
                            name = it.name,
                            imageUrl = it.imageUrl
                        )
                    }
                )
            } else {
                items(count = 3) {
                    MadeCarEmptyImageItem()
                }
            }
        }
    }
}

@Composable
fun MadeCarImageItem(
    modifier: Modifier = Modifier,
    name: String,
    imageUrl: String?,
) {
    Box(
        modifier = modifier
            .size(122.dp)
            .clip(roundCornerSmall)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(all = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
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
fun MadeCarEmptyImageItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(122.dp)
            .background(color = ThinGray, shape = roundCornerSmall),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.my_make_car_option_none),
            style = regular10,
            color = DarkGray
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SavedCarFeed(
    modifier: Modifier = Modifier,
    date: String,
    isPurchase: Boolean,
    modelName: String,
    trim: String,
    trimOptions: List<String>,
    exteriorColor: String,
    interiorColor: String,
    selectedOptions: List<String>,
    review: String?,
    tags: List<String>?,
    onFeedClick: () -> Unit,
    openDeleteDialog: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White, shape = roundCorner)
            .border(width = 1.dp, color = HyundaiSand, shape = roundCorner)
            .clickable { onFeedClick() }
            .padding(horizontal = 16.dp, vertical = 22.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$modelName $trim",
                style = bold18
            )
            XCircle(onClick = openDeleteDialog)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = trimOptions.joinToString(" / "),
                style = regular14
            )
            Spacer(modifier = Modifier.width(4.dp))
            CarFeedDateChip(
                date = date.toDateString(),
                feedType = if (isPurchase) CarFeedType.PURCHASE else CarFeedType.TEST_DRIVE
            )
        }
        Spacer(modifier = Modifier.height(17.dp))
        Row {
            Text(
                text = stringResource(id = R.string.summary_outer),
                style = medium14
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = exteriorColor,
                style = regular14,
                color = MediumDarkGray
            )
            Text(
                text = stringResource(id = R.string.summary_inner),
                style = medium14
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = interiorColor,
                style = regular14,
                color = MediumDarkGray
            )
        }
        if (selectedOptions.isNotEmpty()) {
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.progress_selected_option),
                    style = medium14
                )
                Spacer(modifier = Modifier.width(12.dp))
                SavedCarOptionChip(name = selectedOptions.first())
                val secondChipVisible = selectedOptions.size > 1 && selectedOptions.first().length + selectedOptions[1].length < 18
                val visibleOptionCount = if (secondChipVisible) 2 else 1
                if (secondChipVisible) {
                    Spacer(modifier = Modifier.width(6.dp))
                    SavedCarOptionChip(name = selectedOptions[1])
                }
                if (selectedOptions.size > visibleOptionCount) {
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = "+${selectedOptions.size - visibleOptionCount}", style = medium12, color = DarkGray)
                }
            }
        }
        review?.let {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = HyundaiLightSand, shape = roundCorner)
                    .padding(all = 13.dp),
                text = it,
                style = regular14
            )
        }
        tags?.let {
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                it.forEach { tagName ->
                    OptionTagChip(tagString = tagName)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMadeCarItem() {
    MadeCarFeed(
        modifier = Modifier,
        isTempSaved = true,
        modelName = "팰리세이드",
        trimName = "Le Blanc",
        madeDate = "23년 7월 19일",
        trimOptions = "디젤 2.2 / 4WD / 7인승",
        selectedOptions = listOf(
            ArchiveFeedSelectedOptionUiModel("컴포트 II", "", listOf()),
            ArchiveFeedSelectedOptionUiModel("듀얼 와이드 선루프", "", listOf()),
            ArchiveFeedSelectedOptionUiModel("현대스마트센스 I", "", listOf())
        ),
        onFeedClick = {},
        onDelete = {}
    )
}

