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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.ArchiveSearchPage
import com.softeer.mycarchiving.enums.ArchiveSearchPage.*
import com.softeer.mycarchiving.model.TrimOptionSimpleUiModel
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.archiving.SearchOptionUiModel
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.model.common.CarBasicUiModel
import com.softeer.mycarchiving.model.common.SummaryChildUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionSimpleUiModel
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
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
    modelOption: List<SelectModelUiModel>,
    trimOptions: List<TrimOptionSimpleUiModel>,
    colorOptions: List<ColorOptionSimpleUiModel>,
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
            summaryChildren = modelOption.map { it.toSummary() } + trimOptions.trimsToSummary(),
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

@Composable
fun SearchCarBottomSheetContent(
    modifier: Modifier = Modifier,
    currentPage: ArchiveSearchPage,
    selectedCar: SearchOption,
    pendingCar: SearchOption,
    ableCars: List<SearchOptionUiModel>,
    selectedOptions: List<SearchOption>,
    pendingOptions: List<SearchOption>,
    ableOptions: List<SearchOptionUiModel>,
    ableOptionsSize: Int,
    onOptionChipClick: (SearchOption) -> Unit,
    deleteSelectedOptionChip: (SearchOption) -> Unit,
    deletePendingOptionChip: (SearchOption) -> Unit,
    moveSetCar: () -> Unit,
    moveSetOption: () -> Unit,
    onBackClick: () -> Unit,
    closeSheet: () -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        SearchCarBottomSheetTopArea(
            currentPage = currentPage,
            closeSheet = closeSheet,
            onBackClick = onBackClick
        )
        Divider(thickness = 1.dp, color = ThinGray)
        SearchCarBottomSheetBodyArea(
            modifier = Modifier.weight(1f),
            currentPage =  currentPage,
            selectedCar = selectedCar,
            selectedOptions = selectedOptions,
            ableCars = ableCars,
            ableOptions = ableOptions,
            ableOptionsSize = ableOptionsSize,
            moveSetCar = moveSetCar,
            moveSetOption = moveSetOption,
            onOptionChipClick =  onOptionChipClick,
            deleteSelectedOptionChip =  deleteSelectedOptionChip
        )
        SearchCarBottomSheetBottomArea(
            currentPage = currentPage,
            pendingCar = pendingCar,
            pendingOptions = pendingOptions,
            ableOptionsSize = ableOptionsSize,
            deletePendingOptionChip = deletePendingOptionChip,
            onButtonClick = onButtonClick
        )
    }
}

@Composable
fun SearchCarBottomSheetTopArea(
    modifier: Modifier = Modifier,
    currentPage: ArchiveSearchPage,
    closeSheet: () -> Unit,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        if (currentPage.needBack) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
            }
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
}

@Composable
fun SearchCarBottomSheetBodyArea(
    modifier: Modifier = Modifier,
    currentPage: ArchiveSearchPage,
    selectedCar: SearchOption,
    selectedOptions: List<SearchOption>,
    ableCars: List<SearchOptionUiModel>,
    ableOptions: List<SearchOptionUiModel>,
    ableOptionsSize: Int,
    moveSetCar: () -> Unit,
    moveSetOption: () -> Unit,
    onOptionChipClick: (SearchOption) -> Unit,
    deleteSelectedOptionChip: (SearchOption) -> Unit,
) {
    when(currentPage) {
        SEARCH_CONDITION -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.archive_search_set_car),
                    style = medium16
                )
                Spacer(modifier = Modifier.height(10.dp))
                SearchConditionButton(
                    selectedCar = selectedCar.name,
                    onClick = { moveSetCar() }
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
                            ableOptionsSize
                        ),
                        style = medium12
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                SearchConditionButton(
                    onClick = { moveSetOption() }
                )
                Spacer(modifier = Modifier.height(14.dp))
                SearchDeleteChipFlowList(
                    options = selectedOptions,
                    horizontalSpace = 4,
                    deleteChip = deleteSelectedOptionChip
                )
            }
        }
        SET_CAR -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                ableCars.forEach {
                    SearchCarBottomSheetGridItem(searchOptionUiModel = it)
                }
            }
        }
        SET_OPTION -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                ableOptions.forEachIndexed { index, option ->
                    SearchCarBottomSheetFlowItem(
                        searchOptionUiModel = option,
                        onChipClick = onOptionChipClick
                    )
                    if (index < ableOptions.size - 1) {
                        Divider(thickness = 1.dp, color = ThinGray)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchCarBottomSheetBottomArea(
    modifier: Modifier = Modifier,
    currentPage: ArchiveSearchPage,
    pendingCar: SearchOption,
    pendingOptions: List<SearchOption>,
    ableOptionsSize: Int,
    deletePendingOptionChip: (SearchOption) -> Unit,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(bottom = 20.dp)
    ) {
        when (currentPage) {
            SEARCH_CONDITION -> {/*none*/}
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
                    SearchConditionChip(searchOption = pendingCar)
                }
            }
            SET_OPTION -> {
                AnimatedVisibility(visible = pendingOptions.isNotEmpty()) {
                    Divider(thickness = 1.dp, color = LightGray)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.archive_search_set_option_count, pendingOptions.size, ableOptionsSize),
                            style = medium12
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        SearchDeleteChipFlowList(
                            options = pendingOptions,
                            horizontalSpace = 8,
                            deleteChip = deletePendingOptionChip
                        )
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

@Composable
fun SearchCarBottomSheetGridItem(
    modifier: Modifier = Modifier,
    searchOptionUiModel: SearchOptionUiModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Text(
            text = searchOptionUiModel.category,
            style = medium14,
            color = DarkGray,
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(count = 2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            items(searchOptionUiModel.options) { option ->
                SearchConditionChip(
                    searchOption = option,
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
    searchOptionUiModel: SearchOptionUiModel,
    onChipClick: (SearchOption) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp)
    ) {
        Text(
            text = searchOptionUiModel.category,
            style = medium14,
            color = DarkGray,
        )
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            searchOptionUiModel.options.forEach { option ->
                Box(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    SearchConditionChip(
                        searchOption = option,
                        onClick = { onChipClick(option) },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchDeleteChipFlowList(
    options: List<SearchOption>,
    horizontalSpace: Int,
    deleteChip: (SearchOption) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace.dp)
    ) {
        options.forEach { option ->
            Box(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                SearchConditionChipForDelete(
                    name = option.name,
                    onDelete = { deleteChip(option) },
                )
            }
        }
    }
}


private fun SelectModelUiModel.toSummary(): SummaryChildUiModel =
    SummaryChildUiModel(
        name = name,
        price = price.toPriceString()
    )

private fun List<TrimOptionSimpleUiModel>.trimsToSummary(): List<SummaryChildUiModel> {
    return if (this.isNotEmpty())
        this.fold(0 to "") { acc, trimOption ->
            (acc.first + (trimOption.price)) to (acc.second + trimOption.name + " / ")
        }.run {
            listOf(
                SummaryChildUiModel(
                    name = second.dropLast(3),
                    price = first.toPriceString()
                )
            )
        }
    else
        emptyList()
}

private fun ColorOptionSimpleUiModel.colorToSummary(): SummaryChildUiModel =
    SummaryChildUiModel(
        name = colorName,
        colorPosition = category.take(2),
        imageUrl = imageUrl,
        price = price?.toPriceString()
    )

private fun SelectOptionUiModel.extraToSummary(): SummaryChildUiModel =
    SummaryChildUiModel(
        name = name,
        price = price.toPriceString(),
        imageUrl = imageUrl,
    )


/*@Preview
@Composable
fun PreviewSearchCarBottomSheetContent() {
    val selectedOptions = listOf("컴포트 2 패키지", "듀얼 와이드 선루프", "컴포트 2 패키지", "듀얼 와이드 선루프")

    SearchCarBottomSheetContent(
        currentPage = SET_OPTION,
        selectedCar = "펠리세이드",
        pendingCar = "펠리세이드",
        selectedOptions = selectedOptions,
        pendingOptions = selectedOptions,
        moveSetCar = {},
        moveSetOption = {},
        onBackClick = {},
        closeSheet = {},
        onButtonClick = {},
    )
}*/

/*@Preview
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
}*/
