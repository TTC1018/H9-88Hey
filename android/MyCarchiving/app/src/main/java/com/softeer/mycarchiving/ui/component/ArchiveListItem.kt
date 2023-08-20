package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.CarFeedType
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.MediumDarkGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.util.toDateString

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArchiveFeed(
    modifier: Modifier = Modifier,
    carFeedUiModel: CarFeedUiModel,
    onFeedClick: () -> Unit,
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
            verticalAlignment = Alignment.Top
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "${carFeedUiModel.model} ${carFeedUiModel.trim}",
                style = bold18
            )
            CarFeedDateChip(
                date = carFeedUiModel.creationDate.toDateString(),
                feedType = if (carFeedUiModel.isPurchase) CarFeedType.PURCHASE else CarFeedType.TEST_DRIVE
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = carFeedUiModel.trimOptions.joinToString(" / "),
            style = regular14
        )
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
                    CarFeedOptionChip(name = options[0], equalsFilter = true)
                    if (options.size > 1) {
                        Spacer(modifier = Modifier.width(6.dp))
                        CarFeedOptionChip(name = options[1])
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
fun PreviewArchiveFeed() {
    ArchiveFeed(
        carFeedUiModel = CarFeedUiModel(
            id = "",
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
        onFeedClick = {}
    )
}