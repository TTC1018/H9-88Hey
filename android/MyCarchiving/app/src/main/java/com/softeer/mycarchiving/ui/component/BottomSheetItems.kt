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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import coil.compose.AsyncImage
import com.softeer.data.CarColorType
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.model.common.CarBasicUiModel
import com.softeer.mycarchiving.model.common.SummaryChildUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.ui.theme.ThinGray
import com.softeer.mycarchiving.util.toPriceString

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
        Divider(thickness = 1.dp, color = ThinGray)
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
        AsyncImage(
            modifier = Modifier
                .width(94.dp)
                .height(55.dp)
                .clip(roundCorner),
            model = detailItem.imageUrl,
            contentDescription = "",
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

@Composable
fun SummaryBottomSheetContent(
    modifier: Modifier = Modifier,
    totalPrice: Int,
    trimOptions: List<TrimOptionUiModel>,
    colorOptions: List<ColorOptionUiModel>,
    extraOptions: List<SelectOptionUiModel>,
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
            summaryChildren = colorOptions.map { it.colorToSummary() }
        )
        SummaryLabel(
            modifier = modifier,
            labelName = stringResource(id = R.string.selected_option),
            needCount = true,
            summaryChildren = extraOptions.map { it.extraToSummary() }
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
            Spacer(modifier = Modifier.width(12.dp))
            AsyncImage(
                modifier = Modifier
                    .width(16.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                model = summaryChild.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = summaryChild.name,
            style = regular14
        )
        if (summaryChild.price != null) {
            Text(
                modifier = Modifier.weight(1f),
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
    selectedCar: String,
    selectedOptions: List<String>,
    pendingOptions: List<String>,
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
        Divider(thickness = 1.dp, color = ThinGray)
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .weight(1f),
        ) {
            when (currentPage) {
                SEARCH_CONDITION -> {
                    Text(
                        text = stringResource(id = R.string.archive_search_set_car),
                        style = medium16
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SearchConditionButton(
                        selectedCar = selectedCar,
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Divider(thickness = 1.dp, color = ThinGray)
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
                            text = stringResource(
                                id = R.string.archive_search_set_option_count,
                                selectedOptions.size,
                                15
                            ),
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
                .padding(bottom = 20.dp)
        ) {
            when (currentPage) {
                SEARCH_CONDITION -> {/*none*/ }
                SET_CAR -> {
                    Divider(thickness = 1.dp, color = LightGray)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 18.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.archive_search_set_car_selected_car),
                            style = medium14
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        SearchConditionChip(
                            name = selectedCar,
                            isSelect = true,
                        )
                    }
                }
                SET_OPTION -> {
                    Divider(thickness = 1.dp, color = LightGray)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.archive_search_set_option_count,
                                pendingOptions.size,
                                15
                            ),
                            style = medium12
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            pendingOptions.forEach { option ->
                                Box(
                                    modifier = Modifier.padding(bottom = 8.dp)
                                ) {
                                    SearchConditionChipForDelete(
                                        name = option,
                                        onDelete = {},
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                HyundaiButton(
                    text = if (currentPage == SET_OPTION) {
                        stringResource(
                            id = R.string.archive_search_apply_options,
                            pendingOptions.size
                        )
                    } else {
                        stringResource(id = R.string.archive_search_apply_selected_item)
                    },
                    textColor = White,
                    onClick = onButtonClick,
                )
            }
        }
    }
}

@Composable
fun SearchCarBottomSheetGridItem(
    modifier: Modifier = Modifier,
    category: String,
    options: List<String>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Text(
            text = category,
            style = medium14,
            color = DarkGray,
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            items(options) { option ->
                SearchConditionChip(
                    name = option,
                    onClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchCarBottomSheetFlowItem(
    modifier: Modifier = Modifier,
    category: String,
    options: List<String>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Text(
            text = category,
            style = medium14,
            color = DarkGray,
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            options.forEach { option ->
                Box(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    SearchConditionChip(
                        name = option,
                        onClick = {}
                    )
                }
            }
        }
    }
}

private fun List<TrimOptionUiModel>.trimsToSummary(): List<SummaryChildUiModel> =
    this.fold(0 to "") { acc, trimOptionUiModel ->
        (acc.first + (trimOptionUiModel.price
            ?: 0)) to (acc.second + trimOptionUiModel.optionName + " / ")
    }.run { listOf(SummaryChildUiModel(name = second.dropLast(3), price = first.toPriceString())) }

private fun ColorOptionUiModel.colorToSummary(): SummaryChildUiModel =
    SummaryChildUiModel(
        name = optionName,
        colorPosition = category.type.take(2),
        imageUrl = imageUrl,
        price = price.toPriceString(),
    )

private fun SelectOptionUiModel.extraToSummary(): SummaryChildUiModel =
    SummaryChildUiModel(
        name = name,
        price = price.toPriceString(),
        imageUrl = imageUrl,
    )


@Preview
@Composable
fun PreviewCarBasicBottomSheetContent() {
    val detailItems = listOf(
        CarBasicDetailUiModel(
            name = "ISG 시스템",
            "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
        ),
        CarBasicDetailUiModel(
            name = "ISG 시스템",
            "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
        ),
        CarBasicDetailUiModel(
            "ISG 시스템",
            "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
        )
    )

    val basicItem1 = CarBasicUiModel(category = "파워트레인 성능", detailItems = detailItems)
    val basicItem2 = CarBasicUiModel(category = "지능형 안전 기술", detailItems = detailItems)
    val basicItem3 = CarBasicUiModel(category = "안전", detailItems = detailItems)
    val basicItem4 = CarBasicUiModel(category = "성능", detailItems = detailItems)
    val basicItems = listOf(basicItem1, basicItem2, basicItem3, basicItem4)

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
                imageUrl = "",
                price = 1480000,
                maximumOutput = "202/3,800PS/rpm",
                maximumTorque = "45.0/1,750~2,750kgf-m/rpm"
            ),
            TrimOptionUiModel(
                optionName = "7인승",
                optionDesc = "기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.",
                imageUrl = "",
                price = 0,
            )
        ),
        colorOptions = listOf(
            ColorOptionUiModel(
                category = CarColorType.EXTERIOR,
                optionName = "문라이트 블루펄",
                imageUrl = "",
                price = 0,
                matchingColors = emptyList(),
                tags = emptyList()
            )
        ),
        extraOptions = listOf(
            SelectOptionUiModel(
                id = "",
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "",
                tags = listOf(
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
            )
        ),
    )
}

@Preview
@Composable
fun PreviewSearchCarBottomSheetContent() {
    val selectedOptions = listOf("컴포트 2 패키지", "듀얼 와이드 선루프", "컴포트 2 패키지", "듀얼 와이드 선루프")

    SearchCarBottomSheetContent(
        currentPage = SET_OPTION,
        selectedCar = "펠리세이드",
        selectedOptions = selectedOptions,
        pendingOptions = selectedOptions,
        onBackClick = {},
        closeSheet = {},
        onButtonClick = {},
    )
}

const val category = "SUV"
val options = listOf("펠리세이드", "베뉴", "디 올 뉴 코나", "디 올 뉴 코나Hybrid", "투싼", "투싼Hybrid")

@Preview
@Composable
fun PreviewSearchCarBottomSheetGridItem() {
    SearchCarBottomSheetGridItem(
        category = category,
        options = options
    )
}

@Preview
@Composable
fun PreviewSearchCarBottomSheetFlowItem() {
    SearchCarBottomSheetFlowItem(
        category = category,
        options = options
    )
}
