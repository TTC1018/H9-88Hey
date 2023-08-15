package com.softeer.mycarchiving.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.PrimaryBlue10
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular10
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.util.toPriceString

@Composable
fun OptionSelectItem(
    modifier: Modifier = Modifier,
    option: SelectOptionUiModel,
    onAddClick: () -> Unit,
    focus: Boolean,
    onFocus: () -> Unit,
) {
    Surface(
        modifier = modifier
            .width(160.dp)
            .height(197.dp)
            .clickable { onFocus() },
        shape = roundCorner,
        border = if (focus) BorderStroke(width = 2.dp, color = PrimaryBlue) else null,
        color = if (focus) PrimaryBlue10 else HyundaiLightSand
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(93.dp),
                model = option.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 9.dp, end = 9.dp, top = 10.dp, bottom = 3.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = option.name,
                        style = medium14,
                        color = if (focus) PrimaryBlue else Black
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Text(
                        modifier = modifier
                            .align(Alignment.End),
                        text = stringResource(id = R.string.plus_space_price_won, option.price.toPriceString()),
                        style = medium14,
                        color = if (focus) PrimaryBlue else Black
                    )
                }
                OptionAddButton(modifier = modifier, onClick = onAddClick)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OptionSelectedInfo(
    optionName: String,
    optionTags: List<String>?
) {
    Column {
        OptionHeadText(optionName = optionName)
        Spacer(modifier = Modifier.height(8.dp))
        OptionHeadComment()
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            optionTags?.forEach { tagString ->
                OptionTagChip(tagString = tagString)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExtraOptionCards(
    modifier: Modifier = Modifier,
    options: List<SubSelectOptionUiModel>
) {
    HorizontalPager(
        modifier = modifier,
        pageCount = options.size,
        pageSpacing = 16.dp
    ) { pageNum ->
        ExtraOptionCard(
            option = options[pageNum],
            optionsSize = options.size,
            optionNum = pageNum + 1,
            isMultiple = options.size > 1
        )
    }
}

@Composable
fun ExtraOptionCard(
    modifier: Modifier = Modifier,
    option: SubSelectOptionUiModel,
    optionsSize: Int,
    optionNum: Int = 0,
    isMultiple: Boolean = false
) {
    Box(
        modifier = modifier
            .animateContentSize()
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, PrimaryBlue),
                shape = RoundedCornerShape(8.dp)
            )
            .heightIn(min = if (option.description != null) 140.dp else 70.dp)
            .background(
                color = PrimaryBlue10,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(start = 32.dp, end = 32.dp, top = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = if (option.description != null) Arrangement.spacedBy(8.dp) else Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            ExtraOptionTitle(
                optionNum = optionNum,
                optionName = option.name,
                optionCount = optionsSize,
                isMultiple = isMultiple
            )
            if (option.description != null) {
                ExtraOptionDetail(description = option.description)
            }
        }
    }
}

@Composable
fun ExtraOptionTitle(
    modifier: Modifier = Modifier,
    optionNum: Int,
    optionName: String,
    optionCount: Int,
    isMultiple: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isMultiple) {
            ProgressNumberCircle(
                modifier = Modifier.wrapContentSize(),
                numberText = optionNum.toString().padStart(2, '0'),
                color = PrimaryBlue,
            )
        }
        Row(
            modifier = Modifier
                .weight(0.9f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                style = bold18,
                color = PrimaryBlue,
                text = optionName
            )
            if (isMultiple) {
                ExtraOptionIndicator(
                    modifier = Modifier,
                    optionNum = optionNum,
                    optionCount = optionCount,
                )
            }
        }
    }
}

@Composable
fun ExtraOptionIndicator(
    modifier: Modifier = Modifier,
    optionNum: Int,
    optionCount: Int
) {
    Text(
        modifier = modifier
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        text = stringResource(id = R.string.make_car_option_indicator, optionNum, optionCount),
        color = HyundaiLightSand,
        textAlign = TextAlign.End,
        style = regular10
    )
}

@Composable
fun ExtraOptionDetail(
    description: String?
) {
    if (description != null) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = PrimaryBlue
        )
        ExtraOptionDesc(text = description)
    }
}

@Composable
fun ExtraOptionDesc(
    text: String,
) {
    Text(
        style = regular14,
        color = PrimaryBlue,
        text = text
    )
}

@Preview
@Composable
fun PreviewOptionSelectItem() {
    OptionSelectItem(
        modifier = Modifier,
        option = SelectOptionUiModel(
            id = "",
            name = "컴포트 2",
            price = 1090000,
            imageUrl = ""
        ),
        onAddClick = {},
        focus = true,
        onFocus = {}
    )
}

@Preview(heightDp = 140)
@Composable
fun PreviewOptionSelectedInfo() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OptionSelectedInfo(
            optionName = "컴포트 II",
            optionTags = listOf(
                "어린이🧒",
                "안전사고 예방🚨",
                "대형견도 문제 없어요🐶",
                "가족들도 좋은 옵션👨‍👩‍👧‍👦"
            ),
        )
    }
}

@Preview(heightDp = 60)
@Composable
fun PreviewOptionColorNameSentence() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OptionHeadText(optionName = "퀄팅 천연(블랙)")
        OptionHeadComment()
    }
}

@Preview
@Composable
fun PreviewExtraOptionCardSingle() {
    ExtraOptionCard(
        option = SubSelectOptionUiModel(
            name = "후석 승객 알림",
            imageUrl = "",
        ),
        optionsSize = 0
    )
}

@Preview(widthDp = 340, heightDp = 150)
@Composable
fun PreviewExtraOptionCardWithDesc() {
    ExtraOptionCard(
        option = SubSelectOptionUiModel(
            name = "후석 승객 알림",
            imageUrl = "",
            description = "* 홈페이지의 사진과 설명은 참고용이며 실제 차량에 탑재되는 기능과 설명은 상이할 수 있으니, 차량 구입 전 카마스터를 통해 확인 바랍니다.",
        ),
        optionsSize = 0
    )
}

@Preview(widthDp = 340, heightDp = 150)
@Composable
fun PreviewExtraOptionCardMultiple() {
    ExtraOptionCard(
        option = SubSelectOptionUiModel(
            name = "후석 승객 알림",
            imageUrl = "",
            description = "* 홈페이지의 사진과 설명은 참고용이며 실제 차량에 탑재되는 기능과 설명은 상이할 수 있으니, 차량 구입 전 카마스터를 통해 확인 바랍니다.",
        ),
        optionNum = 1,
        optionsSize = 6,
        isMultiple = true
    )
}

@Preview
@Composable
fun PreviewExtraOptionCards() {
    ExtraOptionCards(
        options = listOf(
            SubSelectOptionUiModel(
                name = "후석 승객 알림",
                imageUrl = "",
                description = "초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다."
            ),
            SubSelectOptionUiModel(
                name = "메탈 리어범퍼스텝",
                imageUrl = "",
                description = "러기지 룸 앞쪽 하단부를 메탈로 만들어 물건을 싣고 내릴 때나 사람이 올라갈 때 차체를 보호해줍니다."
            )
        )
    )
}