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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.CarFeedType
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.common.CarFeedUiModel
import com.softeer.mycarchiving.navigation.ArchivingDestinations
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
    appliedOptions: List<SearchOption>,
    onFeedClick: (Long, ArchivingDestinations?) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White, shape = roundCorner)
            .border(width = 1.dp, color = HyundaiSand, shape = roundCorner)
            .clickable { onFeedClick(carFeedUiModel.id, ArchivingDestinations.ARCHIVING_DETAIL) }
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
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 3.dp),
                text = carFeedUiModel.exteriorColor,
                style = regular14,
                color = MediumDarkGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = stringResource(id = R.string.summary_inner),
                style = medium14
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 3.dp),
                text = carFeedUiModel.interiorColor,
                style = regular14,
                color = MediumDarkGray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        carFeedUiModel.selectedOptions?.let { options ->
            if (options.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.progress_selected_option),
                        style = medium14
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    val appliedOptionIds = appliedOptions.map { it.id }
                    val sortedOptions = sortOptionsByApplied(options = options, appliedOptionIds = appliedOptionIds)
                    CarFeedOptionChip(name = sortedOptions.first().name, equalsFilter = appliedOptionIds.contains(sortedOptions.first().id))
                    val secondChipVisible = sortedOptions.size > 1 && sortedOptions.first().name.length + sortedOptions[1].name.length < 18
                    val visibleOptionCount = if (secondChipVisible) 2 else 1
                    if (secondChipVisible) {
                        Spacer(modifier = Modifier.width(6.dp))
                        CarFeedOptionChip(name = sortedOptions[1].name, equalsFilter = appliedOptionIds.contains(sortedOptions[1].id))
                    }
                    if (options.size > visibleOptionCount) {
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(text = "+${sortedOptions.size - visibleOptionCount}", style = medium12, color = DarkGray)
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

private fun sortOptionsByApplied(options: List<SearchOption>, appliedOptionIds: List<String>): List<SearchOption> {
    val optionsWithApplied = mutableListOf<SearchOption>()
    val optionsWithoutApplied = mutableListOf<SearchOption>()
    options.forEach { option ->
        if (option.id in appliedOptionIds) {
            optionsWithApplied.add(option)
        } else {
            optionsWithoutApplied.add(option)
        }
    }
    return optionsWithApplied + optionsWithoutApplied
}

@Preview
@Composable
fun PreviewArchiveFeed() {
    ArchiveFeed(
        carFeedUiModel = CarFeedUiModel(
            id = 0,
            model = "팰리세이드",
            isPurchase = false,
            creationDate = "2023-07-19",
            trim = "Le Blanc",
            trimOptions = listOf("디젤 2.2", "4WD", "7인승"),
            interiorColor = "문라이트 블루 펄",
            exteriorColor = "퀄팅 천연(블랙)",
            selectedOptions = listOf(),
            review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.",
            tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘", "대형견도 문제 없어요🐶")
        ),
        appliedOptions = listOf(),
        onFeedClick = {_, _ ->}
    )
}