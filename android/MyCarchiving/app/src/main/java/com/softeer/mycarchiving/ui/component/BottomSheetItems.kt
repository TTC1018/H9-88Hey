package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.model.common.CarBasicUiModel
import com.softeer.mycarchiving.model.common.SummaryChildUiModel
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner

val detailItems = listOf(
    CarBasicDetailUiModel(id = 0, detailName = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
    CarBasicDetailUiModel(id = 1, detailName = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
    CarBasicDetailUiModel(id = 2, detailName = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.")
)

val basicItem1 = CarBasicUiModel(id = 0, name = "파워트레인 성능", detailItems = detailItems)
val basicItem2 = CarBasicUiModel(id = 1, name = "지능형 안전 기술", detailItems = detailItems)
val basicItem3 = CarBasicUiModel(id = 2, name = "안전", detailItems = detailItems)
val basicItem4 = CarBasicUiModel(id = 3, name = "성능", detailItems = detailItems)

val basicItems = listOf(basicItem1, basicItem2, basicItem3, basicItem4)

@Composable
fun CarBasicBottomSheetContent(
    modifier: Modifier,
    basicItems: List<CarBasicUiModel>
) {
    LazyColumn(
        modifier = modifier.padding(top = 32.dp, bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = basicItems,
            key = { it.id },
            itemContent = {
                CarBasicItem(modifier = modifier, basicItem = it)
            }
        )
    }
}

@Composable
fun CarBasicItem(
    modifier: Modifier,
    basicItem: CarBasicUiModel
) {
    var showDetail by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = HyundaiLightSand, shape = roundCorner)
                .padding(all = 12.dp)
                .clickable { showDetail = !showDetail },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = basicItem.name,
                style = medium14
            )
            Image(
                painter = if (showDetail) {
                    painterResource(id = R.drawable.ic_up)
                } else {
                    painterResource(id = R.drawable.ic_down)
                },
                contentDescription = null,
                colorFilter = ColorFilter.tint(DarkGray)
            )
        }
        if (showDetail) {
            Column(
                modifier = modifier.padding(top = 12.dp, bottom = 6.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                basicItem.detailItems.forEach { detailItem ->
                    CarBasicDetailItem(modifier = modifier, detailItem = detailItem)
                }
            }
        }
    }

}

@Composable
fun CarBasicDetailItem(
    modifier: Modifier,
    detailItem: CarBasicDetailUiModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = HyundaiNeutral, shape = roundCorner)
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .width(94.dp)
                .height(55.dp)
                .clip(roundCorner),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = modifier.width(17.dp))
        Text(
            text = detailItem.detailName,
            style = regular14
        )
    }
}

val summaryFirst = listOf(
    SummaryChildUiModel(
        name = "펠리세이드 Le Blanc(르블랑)",
        price = "47,3400,000",
        needPlus = false
    ),
    SummaryChildUiModel(
        name = "디젤 2.2 / 4WD / 7인승",
        price = "1,090,000",
    )
)

val summarySecond = listOf(
    SummaryChildUiModel(
        name = "문라이트 블루펄",
        colorPosition = "외장"
    ),
    SummaryChildUiModel(
        name = "퀼팅 천연(블랙)",
        colorPosition = "내장",
    )
)

val summaryThird = listOf(
    SummaryChildUiModel(
        name = "컴포트 ||",
        price = "1,090,000",
    ),
    SummaryChildUiModel(
        name = "컴포트 ||",
        price = "1,090,000",
    ),
    SummaryChildUiModel(
        name = "컴포트 ||",
        price = "1,090,000",
    ),
    SummaryChildUiModel(
        name = "컴포트 ||",
        price = "1,090,000",
    ),
    SummaryChildUiModel(
        name = "컴포트 ||",
        price = "1,090,000",
    ),
)

const val totalPrice = "47,720,000"

@Composable
fun SummaryBottomSheetContent(
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.show_summary),
            style = medium18
        )
        Spacer(modifier = modifier.height(16.dp))
        SummaryLabel(
            modifier = Modifier,
            labelName = stringResource(id = R.string.summary_total_price),
            totalPrice = totalPrice,
            summaryChildren = summaryFirst
        )
        SummaryLabel(
            modifier = modifier,
            labelName = stringResource(id = R.string.summary_color),
            summaryChildren = summarySecond
        )
        SummaryLabel(
            modifier = modifier,
            labelName = stringResource(id = R.string.selected_option),
            needCount = true,
            summaryChildren = summaryThird
        )
    }
}

@Composable
fun SummaryLabel(
    modifier: Modifier,
    labelName: String,
    totalPrice: String? = null,
    needCount: Boolean = false,
    summaryChildren: List<SummaryChildUiModel>? = null
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(42.dp)
                .background(HyundaiLightSand)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = labelName,
                style = medium14
            )
            if (needCount && summaryChildren != null) {
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = summaryChildren.size.toString()
                )
            }
            if (totalPrice != null) {
                Text(
                    modifier = modifier.weight(1f),
                    text = totalPrice,
                    style = bold18,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.won),
                    style = medium12
                )
            }
        }
        LazyColumn(
            modifier = modifier.padding(vertical = 20.dp),
            userScrollEnabled = false,
        ) {
            if (summaryChildren != null) {
                items(
                    items = summaryChildren,
                    itemContent = {
                        SummaryChild(modifier = modifier, summaryChild = it)
                    }
                )
            }
        }
    }
}

@Composable
fun SummaryChild(
    modifier: Modifier,
    summaryChild: SummaryChildUiModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (summaryChild.colorPosition != null) {
            Text(
                text = summaryChild.colorPosition,
                style = medium14
            )
            Spacer(modifier = modifier.width(12.dp))
            Image(
                modifier = modifier
                    .size(16.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Spacer(modifier = modifier.width(12.dp))
        }
        Text(
            text = summaryChild.name,
            style = regular14
        )
        if (summaryChild.price != null) {
            Text(
                modifier = modifier.weight(1f),
                text = if (summaryChild.needPlus) {
                    stringResource(id = R.string.plus_price_won, summaryChild.price)
                } else {
                    stringResource(id = R.string.price_won, summaryChild.price)
                },
                style = medium14,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
fun PreviewCarBasicBottomSheetContent() {
    CarBasicBottomSheetContent(modifier = Modifier, basicItems = basicItems)
}

@Preview
@Composable
fun PreviewSummaryBottomSheetContent() {
    SummaryBottomSheetContent(modifier = Modifier)
}
