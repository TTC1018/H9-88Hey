package com.softeer.mycarchiving.ui.makingcar.selectoption

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.softeer.domain.model.CarDetails
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import com.softeer.mycarchiving.ui.component.CarBasicBottomSheetContent
import com.softeer.mycarchiving.ui.component.CarBasicItemButton
import com.softeer.mycarchiving.ui.component.ExtraOptionCard
import com.softeer.mycarchiving.ui.component.ExtraOptionCards
import com.softeer.mycarchiving.ui.component.OptionSelectItem
import com.softeer.mycarchiving.ui.component.OptionSelectedInfo
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.makingcar.loading.LoadingScreen
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.util.TRIM_EXTRA
import com.softeer.mycarchiving.util.TRIM_HGENUINE
import com.softeer.mycarchiving.util.TRIM_NPERFORMANCE
import com.softeer.mycarchiving.util.TRIM_OPTION
import com.softeer.mycarchiving.util.fadeInAndOut

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOptionRoute(
    modifier: Modifier = Modifier,
    mainProgress: Int,
    screenProgress: Int,
    viewModelStoreOwner: ViewModelStoreOwner,
    viewModel: SelectOptionViewModel = hiltViewModel(),
    sharedViewModel: MakingCarViewModel = hiltViewModel(viewModelStoreOwner)
) {
    val scrollState = rememberScrollState()
    val selectOptions by viewModel.selectOptions.collectAsStateWithLifecycle()
    val hGenuines by viewModel.hGenuines.collectAsStateWithLifecycle()
    val nPerformances by viewModel.nPerformances.collectAsStateWithLifecycle()
    val focusedOptionIndex by viewModel.focusedOptionIndex.collectAsStateWithLifecycle()
    val basicItems by viewModel.basicOptions.collectAsStateWithLifecycle()
    val showBasicItems by viewModel.showBasicItems.collectAsStateWithLifecycle()
    val selectedExtras by sharedViewModel.selectedExtraOptions.collectAsStateWithLifecycle()
    val selectedHGenuines by sharedViewModel.selectedHGenuines.collectAsStateWithLifecycle()
    val selectedNPerformance by sharedViewModel.selectedNPerformance.collectAsStateWithLifecycle()
    val extraTagMap by viewModel.selectOptionTagMap.collectAsStateWithLifecycle()
    val hGenuineTagMap by viewModel.hGeniuneTagMap.collectAsStateWithLifecycle()
    val nPerformanceTagMap by viewModel.nPerformanceTagMap.collectAsStateWithLifecycle()
    val carDetails by sharedViewModel.carDetails.observeAsState()
    val isArchived = carDetails?.selectedOptions.isNullOrEmpty().not() &&
            (selectedExtras.isEmpty() || selectedHGenuines.isEmpty() || selectedNPerformance.isEmpty())

    if (isArchived) {
        InitArchiveDataEffect(
            carDetails = carDetails,
            extraOptions = selectOptions,
            hGenuines = hGenuines,
            nPerformances = nPerformances,
            isArchived = isArchived,
            saveSelectOption = sharedViewModel::updateSelectedExtraOption,
        )
    }

    LaunchedEffect(screenProgress) {
        viewModel.focusOptionItem(0) // 화면 변할 때마다 focus된 아이템 초기화

        // 방금 전 상태까지 임시 저장
        sharedViewModel.saveTempCarInfo()
    }

    SelectOptionScreen(
        modifier = modifier,
        scrollState = scrollState,
        screenProgress = screenProgress,
        options = when (mainProgress to screenProgress) {
            TRIM_OPTION to TRIM_EXTRA -> selectOptions
            TRIM_OPTION to TRIM_HGENUINE -> hGenuines
            TRIM_OPTION to TRIM_NPERFORMANCE -> nPerformances
            else -> emptyList()
        },
        selectedOptions = when (screenProgress) {
            0 -> selectedExtras
            1 -> selectedHGenuines
            2 -> selectedNPerformance
            else -> emptyList()
        },
        tags = when (mainProgress to screenProgress) {
            TRIM_OPTION to TRIM_EXTRA -> extraTagMap
            TRIM_OPTION to TRIM_HGENUINE -> hGenuineTagMap
            TRIM_OPTION to TRIM_NPERFORMANCE -> nPerformanceTagMap
            else -> emptyMap()
        },
        focusedIndex = focusedOptionIndex,
        focusOption = viewModel::focusOptionItem,
        showBasicItems = viewModel::openBasicItems,
        onAddOption = sharedViewModel::updateSelectedExtraOption,
    )
    if (showBasicItems) {
        ModalBottomSheet(
            onDismissRequest = viewModel::closeBasicItems,
            containerColor = White,
            sheetState = SheetState(skipPartiallyExpanded = true),
            windowInsets = WindowInsets(top = 60.dp),
            scrimColor = Color.Transparent,
            dragHandle = null
        ) {
            CarBasicBottomSheetContent(
                basicItems = basicItems,
                closeBasicItems = viewModel::closeBasicItems
            )
        }
    }
}

@Composable
fun SelectOptionScreen(
    modifier: Modifier,
    scrollState: ScrollState,
    screenProgress: Int,
    options: List<SelectOptionUiModel>,
    selectedOptions: List<SelectOptionUiModel>?,
    tags: Map<String, List<String>>,
    focusedIndex: Int,
    focusOption: (Int) -> Unit,
    showBasicItems: () -> Unit,
    onAddOption: (SelectOptionUiModel, Int, Boolean) -> Unit,
) {
    val context = LocalContext.current
    var imageLoadCounter by remember { mutableIntStateOf(0) }
    val imageLoadEnded by remember(imageLoadCounter) {
        derivedStateOf {
            imageLoadCounter != 0 && imageLoadCounter == options.size
        }
    }
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }
            .build()
    }

    LaunchedEffect(options) {
        imageLoadCounter = 0
        options.forEach { option ->
            imageLoader.execute(
                ImageRequest.Builder(context)
                    .data(option.imageUrl)
                    .memoryCacheKey(option.imageUrl)
                    .listener { _, _ -> imageLoadCounter++ }
                    .build()
            )
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = imageLoadEnded,
            transitionSpec = { fadeInAndOut() },
            label = "",
        ) { loadEnded ->
            when (loadEnded) {
                true -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = White)
                            .verticalScroll(scrollState)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, top = 14.dp, bottom = 24.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(
                                        id = R.string.make_car_selected_option,
                                        options.size
                                    ),
                                    style = medium16,
                                    color = DarkGray
                                )
                                Box(modifier = Modifier.padding(end = 16.dp)) {
                                    CarBasicItemButton(onClick = showBasicItems)
                                }
                            }
                            Spacer(modifier = Modifier.height(13.dp))
                            LazyRow(
                                modifier = Modifier.padding(end = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                itemsIndexed(
                                    items = options,
                                    itemContent = { idx, option ->
                                        OptionSelectItem(
                                            modifier = modifier,
                                            option = option,
                                            focus = focusedIndex == idx,
                                            isAdded = selectedOptions?.any { it.id == option.id }
                                                ?: false,
                                            onAddClick = { onAddOption(option, screenProgress, false) },
                                            onFocus = { focusOption(idx) }
                                        )
                                    }
                                )
                            }
                        }
                        Divider(thickness = 6.dp, color = HyundaiLightSand)
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 24.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OptionSelectedInfo(
                                optionName = options.getOrNull(focusedIndex)?.name ?: "",
                                optionTags = tags.getOrDefault(
                                    options.getOrNull(focusedIndex)?.id,
                                    emptyList()
                                )
                            )
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .clip(shape = roundCorner),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(options.getOrNull(focusedIndex)?.imageUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                imageLoader = imageLoader
                            )
                            options.getOrNull(focusedIndex)?.subOptions?.let {
                                if (it.size > 1) {
                                    ExtraOptionCards(options = it)
                                } else {
                                    ExtraOptionCard(option = it[0], optionsSize = 1)
                                }
                            }
                        }
                    }
                }
                false -> LoadingScreen {}
            }
        }
    }

}

@Composable
private fun InitArchiveDataEffect(
    carDetails: CarDetails?,
    extraOptions: List<SelectOptionUiModel>,
    hGenuines: List<SelectOptionUiModel>,
    nPerformances: List<SelectOptionUiModel>,
    isArchived: Boolean,
    saveSelectOption: (SelectOptionUiModel, Int, Boolean) -> Unit
) {
    val optionIds =
        remember(carDetails) { carDetails?.selectedOptions?.map { it.id }?.toSet() ?: emptySet() }

    LaunchedEffect(extraOptions) {
        carDetails?.run {
            extraOptions.filter { it.id in optionIds }
                .forEach { saveSelectOption(it, TRIM_EXTRA, isArchived) }
        }
    }

    LaunchedEffect(hGenuines) {
        carDetails?.run {
            hGenuines.filter { it.id in optionIds }
                .forEach { saveSelectOption(it, TRIM_HGENUINE, isArchived) }
        }
    }

    LaunchedEffect(nPerformances) {
        carDetails?.run {
            nPerformances.filter { it.id in optionIds }
                .forEach { saveSelectOption(it, TRIM_NPERFORMANCE, isArchived) }
        }
    }
}

@Preview
@Composable
fun PreviewSelectOptionScreen() {
    SelectOptionScreen(
        modifier = Modifier,
        scrollState = rememberScrollState(),
        screenProgress = 0,
        options = listOf(
            SelectOptionUiModel(
                id = "1",
                name = "컴포트 2",
                price = 1090000,
                imageUrl = "",
                subOptions = listOf(
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
                ),
            ),
            SelectOptionUiModel(
                id = "2",
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "",
                tags = listOf(
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
            )
        ),
        selectedOptions = listOf(
            SelectOptionUiModel(
                id = "2",
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "",
                tags = listOf(
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
            )
        ),
        tags = mapOf(
            "2" to listOf(
                "대형견도 문제 없어요",
                "가족들도 좋은 옵션👨‍👩‍👧‍👦"
            )
        ),
        focusedIndex = 0,
        focusOption = {},
        showBasicItems = {},
        onAddOption = { _, _, _ -> }
    )
}