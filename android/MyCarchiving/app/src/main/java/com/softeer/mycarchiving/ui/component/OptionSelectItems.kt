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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.OptionUiModel
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

@Composable
fun OptionSelectItem(
    modifier: Modifier,
    optionName: String,
    optionPrice: String,
    onItemClick: () -> Unit,
    onAddClick: () -> Unit
) {
    var itemSelect by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier
            .width(160.dp)
            .height(197.dp)
            .clickable {
                itemSelect = !itemSelect
                onItemClick()
            },
        shape = roundCorner,
        border = if (itemSelect) BorderStroke(width = 2.dp, color = PrimaryBlue) else null,
        color = if (itemSelect) PrimaryBlue10 else HyundaiLightSand
    ) {
        Column {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(93.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 9.dp, end = 9.dp, top = 10.dp, bottom = 3.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = optionName,
                        style = medium14,
                        color = if (itemSelect) PrimaryBlue else Black
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Text(
                        modifier = modifier
                            .align(Alignment.End),
                        text = stringResource(id = R.string.plus_space_price_won, optionPrice),
                        style = medium14,
                        color = if (itemSelect) PrimaryBlue else Black
                    )
                }
                OptionAddButton(modifier = modifier, onClick = onAddClick)
            }
        }
    }
}

@Preview
@Composable
fun PreviewOptionSelectItem() {
    OptionSelectItem(modifier = Modifier, optionName = "컴포트 2", optionPrice = "1,090,000", onItemClick = {}, onAddClick = {})
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OptionSelectedInfo(
    optionTags: List<String>
) {
    OptionHeadText(optionName = "컴포트 II")
    OptionHeadComment()
    FlowRow(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        optionTags.forEach { tagString ->
            OptionTagChip(tagString = tagString)
        }
    }
}

@Preview(heightDp = 140)
@Composable
fun PreviewOptionSelectedInfo() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OptionSelectedInfo(
            optionTags = listOf(
                "어린이🧒",
                "안전사고 예방🚨",
                "대형견도 문제 없어요🐶",
                "가족들도 좋은 옵션👨‍👩‍👧‍👦"
            )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExtraOptionCards(
    modifier: Modifier = Modifier,
    options: List<OptionUiModel>
) {
    HorizontalPager(
        modifier = modifier,
        pageCount = options.size,
        pageSpacing = 16.dp
    ) { pageNum ->
        val (name, desc) = options[pageNum]
        ExtraOptionCard(
            optionCount = options.size,
            optionNum = pageNum + 1,
            optionName = name,
            description = desc,
            isMultiple = options.size > 1
        )
    }
}

@Composable
fun ExtraOptionCard(
    modifier: Modifier = Modifier,
    optionNum: Int = 0,
    optionCount: Int,
    optionName: String,
    description: String?,
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
            .heightIn(min = if (description != null) 140.dp else 70.dp)
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
            verticalArrangement = if (description != null) Arrangement.spacedBy(8.dp) else Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            ExtraOptionTitle(
                optionNum = optionNum,
                optionName = optionName,
                optionCount = optionCount,
                isMultiple = isMultiple
            )
            if (description != null) {
                ExtraOptionDetail(description = description)
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
    modifier: Modifier = Modifier,
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
fun PreviewExtraOptionCardSingle() {
    ExtraOptionCard(
        optionName = "듀얼 머플러 패키지",
        description = null,
        optionCount = 0
    )
}

@Preview(widthDp = 340, heightDp = 150)
@Composable
fun PreviewExtraOptionCardWithDesc() {
    ExtraOptionCard(
        optionName = "20인치 다크 스퍼터링 힐",
        description = "* 홈페이지의 사진과 설명은 참고용이며 실제 차량에 탑재되는 기능과 설명은 상이할 수 있으니, 차량 구입 전 카마스터를 통해 확인 바랍니다.",
        optionCount = 0
    )
}

@Preview(widthDp = 340, heightDp = 150)
@Composable
fun PreviewExtraOptionCardMultiple() {
    ExtraOptionCard(
        optionName = "헤드업 디스플레이",
        description = "주요 주행 정보를 전면 윈드실드에 표시하며, 밝기가 최적화되어 주간에도 시인성이 뛰어납니다.",
        optionNum = 1,
        optionCount = 6,
        isMultiple = true
    )
}

@Preview
@Composable
fun PreviewExtraOptionCards() {
    ExtraOptionCards(
        options = listOf(
            OptionUiModel("헤드업 디스플레이", "주요 주행 정보를 전면 윈드실드에 표시하며, 밝기가 최적화되어 주간에도 시인성이 뛰어납니다."),
            OptionUiModel("3열 열선시트", "시동이 걸린 상태에서 해당 좌석 히터 스위치를 누르면 강약조절 표시등이 켜져 사용 중임을 나타내고 해당 좌석이 따뜻해집니다.")
        )
    )
}