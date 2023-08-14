package com.softeer.mycarchiving.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import com.softeer.mycarchiving.model.TrimOptionUiModel
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
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.ui.theme.thinGray
import com.softeer.mycarchiving.util.toPriceString

val detailItems = listOf(
    CarBasicDetailUiModel(name = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
    CarBasicDetailUiModel(name = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
    CarBasicDetailUiModel("ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.")
)

val basicItem1 = CarBasicUiModel(category = "파워트레인 성능", detailItems = detailItems)
val basicItem2 = CarBasicUiModel(category = "지능형 안전 기술", detailItems = detailItems)
val basicItem3 = CarBasicUiModel(category = "안전", detailItems = detailItems)
val basicItem4 = CarBasicUiModel(category = "성능", detailItems = detailItems)

val basicItems = listOf(basicItem1, basicItem2, basicItem3, basicItem4)

@Composable
fun CarBasicBottomSheetContent(
    modifier: Modifier = Modifier,
    basicItems: List<CarBasicUiModel>,
    closeBasicItems: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.make_car_basic_item),
                style = medium16
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { closeBasicItems() },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )
        }
        Divider(thickness = 1.dp, color = thinGray)
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            basicItems.forEach {
                CarBasicItem(basicItem = it)
            }
        }
    }
}

@Composable
fun CarBasicItem(
    modifier: Modifier = Modifier,
    basicItem: CarBasicUiModel
) {
    var showDetail by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = HyundaiLightSand, shape = roundCorner)
                .clickable { showDetail = !showDetail }
                .padding(all = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = basicItem.category,
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
        AnimatedVisibility(visible = showDetail) {
            Column(
                modifier = modifier.padding(top = 12.dp, bottom = 6.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                basicItem.detailItems.forEach {
                    CarBasicDetailItem(detailItem = it)
                }
            }
        }
    }

}

@Composable
fun CarBasicDetailItem(
    modifier: Modifier = Modifier,
    detailItem: CarBasicDetailUiModel
) {
    var detailDialogShow by remember { mutableStateOf(false) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = HyundaiNeutral, shape = roundCorner)
            .clickable { detailDialogShow = true }
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(94.dp)
                .height(55.dp)
                .clip(roundCorner),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.width(17.dp))
        Text(
            text = detailItem.name,
            style = regular14
        )
    }
    if (detailDialogShow) {
        BasicItemDialog(
            onDismissRequest = {
               detailDialogShow = false
            },
            detailItem = detailItem
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

@Composable
fun SummaryBottomSheetContent(
    modifier: Modifier = Modifier,
    totalPrice: Int,
    trimOptions: List<TrimOptionUiModel>,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.show_summary),
            style = medium18
        )
        Spacer(modifier = modifier.height(16.dp))
        SummaryLabel(
            modifier = Modifier,
            labelName = stringResource(id = R.string.summary_total_price),
            totalPrice = totalPrice,
            summaryChildren = trimOptions.trimsToSummary(),
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
    totalPrice: Int? = null,
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
                    text = totalPrice.toPriceString(),
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
        Spacer(modifier = Modifier.height(10.dp))
        summaryChildren?.forEach { summaryChild ->
            SummaryChild(summaryChild = summaryChild)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun SummaryChild(
    modifier: Modifier = Modifier,
    summaryChild: SummaryChildUiModel,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchCarBottomSheetContent(
    modifier: Modifier = Modifier,
    currentPage: ArchiveSearchPage,
    selectedOptions: List<String>,
    onBackClick: () -> Unit,
    closeSheet: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            if (currentPage.needBack) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable { onBackClick() },
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
            Text(
                modifier = modifier.align(Alignment.Center),
                text = stringResource(id = currentPage.titleTextId),
                style = medium16,
                fontSize = if (currentPage == SEARCH_CONDITION) 16.sp else 14.sp,
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { closeSheet() },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )
        }
        Divider(thickness = 1.dp, color = thinGray)
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .weight(1f),
        ) {
            when(currentPage) {
                SEARCH_CONDITION -> {
                    Text(
                        text = stringResource(id = R.string.archive_search_set_car),
                        style = medium16
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SearchConditionButton(
                        selectedCar = "펠리세이드",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(thickness = 1.dp, color = thinGray)
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = stringResource(id = R.string.archive_search_set_option),
                            style = medium16
                        )
                        Text(
                            text = stringResource(id = R.string.archive_search_set_option_count, selectedOptions.size, 15),
                            style = medium12
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    SearchConditionButton(
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        selectedOptions.forEach {
                            SearchConditionChipForDelete(
                                name = it,
                                onDelete = {}
                            )
                        }
                    }
                }
                SET_CAR -> {

                }
                SET_OPTION -> {

                }
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
        ) {
            HyundaiButton(
                text = if (currentPage == SET_OPTION) {
                    stringResource(id = R.string.archive_search_apply_options, 0)
                } else {
                    stringResource(id = R.string.archive_search_apply_selected_item)
                },
                textColor = White,
                onClick = onButtonClick,
            )
        }
    }
}

private fun List<TrimOptionUiModel>.trimsToSummary(): List<SummaryChildUiModel> =
    this.fold(0 to "") { acc, trimOptionUiModel ->
        (acc.first + (trimOptionUiModel.price
            ?: 0)) to (acc.second + trimOptionUiModel.optionName + " / ")
    }.run { listOf(SummaryChildUiModel(name = second.dropLast(3), price = first.toPriceString())) }

@Preview
@Composable
fun PreviewCarBasicBottomSheetContent() {
    CarBasicBottomSheetContent(basicItems = basicItems) {}
}

@Preview
@Composable
fun PreviewSummaryBottomSheetContent() {
    SummaryBottomSheetContent(
        modifier = Modifier,
        totalPrice = 47720000,
        trimOptions = listOf(
            TrimOptionUiModel(
                optionName = "디젤 2.2",
                optionDesc = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.",
                imageUrl = null,
                price = 1480000,
                maximumOutput = "202/3,800PS/rpm",
                maximumTorque = "45.0/1,750~2,750kgf-m/rpm"
            ),
            TrimOptionUiModel(
                optionName = "7인승",
                optionDesc = "기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.",
                imageUrl = null,
                price = 0,
            )
        ),
    )
}

val selectedOptions = listOf("컴포트 2 패키지", "듀얼 와이드 선루프")

@Preview
@Composable
fun PreviewSearchCarBottomSheetContent() {
    SearchCarBottomSheetContent(
        currentPage = SEARCH_CONDITION,
        selectedOptions = selectedOptions,
        onBackClick = {},
        closeSheet = {},
        onButtonClick = {},
    )
}
