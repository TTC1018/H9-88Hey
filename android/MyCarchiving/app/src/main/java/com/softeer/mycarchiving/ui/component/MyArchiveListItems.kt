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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.model.myarchive.MadeCarSelectedOptionUiModel
import com.softeer.mycarchiving.ui.theme.AlertPrimary
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.DarkGray
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
import com.softeer.mycarchiving.ui.theme.roundCornerSmall
import com.softeer.mycarchiving.util.toDateString


@Composable
fun MadeCarItem(
    modifier: Modifier = Modifier,
    isTempSaved: Boolean,
    modelName: String,
    trimName: String,
    madeDate: String,
    options: String,
    imageInfos: List<MadeCarSelectedOptionUiModel>,
    onItemClick: () -> Unit,
    onDelete: () -> Unit
) {
    var shouldDialogShow by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(start = 22.dp, top = 20.dp, bottom = 20.dp)
            .clickable { onItemClick() }
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
                    date = madeDate,
                    feedType = if (isTempSaved) CarFeedType.TEMP_SAVE else CarFeedType.MADE
                )
                XCircle(
                    modifier = Modifier,
                    onClick = { shouldDialogShow = true }
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = options,
            style = regular14
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(
                items = imageInfos,
                itemContent = {
                    MadeCarImageItem(
                        modifier = Modifier,
                        name = it.name,
                        imageUrl = it.imageUrl
                    )
                }
            )
        }
    }

    if (shouldDialogShow) {
        DeleteMadeCarDialog(
            onDismissRequest = { shouldDialogShow = false },
            onDelete = onDelete,
            carName = "$modelName $trimName"
        )
    }
}

@Composable
fun MadeCarImageItem(
    modifier: Modifier,
    name: String,
    imageUrl: String,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SavedFeed(
    modifier: Modifier = Modifier,
    carFeedUiModel: CarFeedUiModel,
    onFeedClick: () -> Unit,
    onDelete: () -> Unit,
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
                text = "${carFeedUiModel.model} ${carFeedUiModel.trim}",
                style = bold18
            )
            XCircle(onClick = onDelete)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = carFeedUiModel.trimOptions.joinToString(" / "),
                style = regular14
            )
            Spacer(modifier = Modifier.width(4.dp))
            CarFeedDateChip(
                date = carFeedUiModel.creationDate.toDateString(),
                feedType = if (carFeedUiModel.isPurchase) CarFeedType.PURCHASE else CarFeedType.TEST_DRIVE
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
                text = carFeedUiModel.exteriorColor,
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
                text = carFeedUiModel.interiorColor,
                style = regular14,
                color = MediumDarkGray
            )
        }
        if (carFeedUiModel.selectedOptions?.isNotEmpty() == true) {
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.progress_selected_option),
                    style = medium14
                )
                Spacer(modifier = Modifier.width(12.dp))
                carFeedUiModel.selectedOptions.let { options ->
                    SavedCarOptionChip(name = options[0])
                    if (options.size > 1) {
                        Spacer(modifier = Modifier.width(6.dp))
                        SavedCarOptionChip(name = options[1])
                    }
                    if (options.size > 2) {
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(text = "+${options.size - 2}", style = medium12, color = DarkGray)
                    }
                }
            }
        }
        carFeedUiModel.review?.let { review ->
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = HyundaiLightSand, shape = roundCorner)
                    .padding(all = 13.dp),
                text = review,
                style = regular14
            )
        }
        carFeedUiModel.tags?.let { tags ->
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                tags.forEach {
                    OptionTagChip(tagString = it)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMadeCarItem() {
    MadeCarItem(
        modifier = Modifier,
        isTempSaved = true,
        modelName = "팰리세이드",
        trimName = "Le Blanc",
        madeDate = "23년 7월 19일",
        options = "디젤 2.2 / 4WD / 7인승",
        imageInfos = listOf(
            MadeCarSelectedOptionUiModel("컴포트 II", ""),
            MadeCarSelectedOptionUiModel("듀얼 와이드 선루프", ""),
            MadeCarSelectedOptionUiModel("현대스마트센스 I", "")
        ),
        onItemClick = {},
        onDelete = {}
    )
}

@Preview
@Composable
fun PreviewSavedFeed() {
    SavedFeed(
        carFeedUiModel = CarFeedUiModel(
            id = 0,
            model = "팰리세이드",
            isPurchase = false,
            creationDate = "2023-07-19",
            trim = "Le Blanc",
            trimOptions = listOf("디젤 2.2", "4WD", "7인승"),
            interiorColor = "문라이트 블루 펄",
            exteriorColor = "퀄팅 천연(블랙)",
            selectedOptions = listOf("컴포트 ||", "듀얼 와이드 선루프"),
            review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
            tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘", "대형견도 문제 없어요🐶")
        ),
        onFeedClick = {},
        onDelete = {}
    )
}

