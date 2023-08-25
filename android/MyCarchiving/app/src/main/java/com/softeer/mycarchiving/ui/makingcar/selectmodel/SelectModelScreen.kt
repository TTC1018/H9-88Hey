package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.memory.MemoryCache
import coil.request.ImageRequest
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import com.softeer.mycarchiving.ui.component.CarImageSelectItem
import com.softeer.mycarchiving.ui.component.OptionCardForModel
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.makingcar.loading.LoadingScreen
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.util.fadeInAndOut
import kotlinx.coroutines.launch

@Composable
fun SelectModelRoute(
    modifier: Modifier = Modifier,
    viewModelOwner: ViewModelStoreOwner,
    viewModel: SelectModelViewModel = hiltViewModel(),
    sharedViewModel: MakingCarViewModel = hiltViewModel(viewModelOwner)
) {
    val carModels by viewModel.carModels.collectAsStateWithLifecycle()
    val carImages by viewModel.carImages.collectAsStateWithLifecycle()
    val focusedImageIndex by viewModel.focusedImageIndex.collectAsStateWithLifecycle()
    val carDetails by sharedViewModel.carDetails.observeAsState()
    val selectedColor by sharedViewModel.selectedColor.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val selectedModel by sharedViewModel.selectedModelInfo.observeAsState()
    val fromArchive by sharedViewModel.archivingStartedFlag
    val isArchived = carDetails != null && selectedModel == null

    LaunchedEffect(isArchived, carModels) {
        if (isArchived) {
            // 아카이빙에서 왔다면 초기화 후 해당 데이터 선택
            sharedViewModel.initializeSelectedOptions()

            carDetails?.trim?.run {
                carModels.find { it.id == id }?.let {
                    sharedViewModel.updateSelectedModelInfo(it, true)
                }
            }
        }

        // 아무것도 선택된 것 없을 때 첫번째 모델 자동 선택
        if (isArchived.not() && selectedModel == null) {
            carModels.firstOrNull()?.let { sharedViewModel.updateSelectedModelInfo(it) }
        }
    }

    SelectModelScreen(
        modifier = modifier,
        scrollState = scrollState,
        carModels = carModels,
        carImages = carImages,
        focusedImageIndex = focusedImageIndex,
        shouldShowDialog = fromArchive || selectedColor.isNotEmpty(),
        onCarImageClick = viewModel::onCarImageClick,
        selectedModel = selectedModel,
        onModelSelect = sharedViewModel::updateSelectedModelInfo,
        onInitialize = sharedViewModel::initializeByChangedModel,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectModelScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    carModels: List<SelectModelUiModel>,
    carImages: List<String>,
    focusedImageIndex: Int,
    selectedModel: SelectModelUiModel?,
    shouldShowDialog: Boolean,
    onCarImageClick: (Int) -> Unit,
    onModelSelect: (SelectModelUiModel) -> Unit,
    onInitialize: () -> Unit,
) {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25)
                    .build()
            }.build()
    }
    var loadCounter by remember { mutableIntStateOf(0) }
    val imageLoaded by remember(loadCounter) { derivedStateOf { loadCounter != 0 && loadCounter == carImages.size } }

    LaunchedEffect(carImages) {
        carImages.forEach {
            launch {
                imageLoader.execute(
                    ImageRequest.Builder(context)
                        .data(it)
                        .listener { _, _ ->
                            loadCounter++
                        }.build()
                )
            }
        }
    }

    AnimatedContent(
        targetState = imageLoaded,
        transitionSpec = { fadeInAndOut() },
        label = ""
    ) {
        when (it) {
            true -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = White)
                        .verticalScroll(scrollState)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(254.dp),
                        model = carImages.getOrNull(focusedImageIndex),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        imageLoader = imageLoader
                    )
                    Column(
                        modifier = Modifier.padding(all = 16.dp)
                    ) {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            for (i in carImages.indices) {
                                CarImageSelectItem(
                                    modifier = modifier,
                                    onItemClick = { onCarImageClick(i) },
                                    isSelect = i == focusedImageIndex,
                                    imageUrl = carImages[i],
                                    imageCache = imageLoader
                                )
                            }
                        }

                        Column(
                            modifier = Modifier.padding(vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            carModels.forEachIndexed { index, carModel ->
                                OptionCardForModel(
                                    carModelIndex = index,
                                    carModel = carModel,
                                    isExpanded = carModel.id == selectedModel?.id,
                                    shouldInitialize = shouldShowDialog,
                                    onClick = { onModelSelect(carModel) },
                                    onInitialize = onInitialize
                                )
                            }
                        }
                    }
                }
            }

            false -> LoadingScreen {}
        }
    }

}

@Preview
@Composable
fun PreviewSelectModelScreen() {
    SelectModelScreen(
        scrollState = rememberScrollState(),
        carModels = listOf(
            SelectModelUiModel(
                id = 1,
                name = "르블랑",
                price = 40000000,
                features = emptyList()
            )
        ),
        carImages = emptyList(),
        focusedImageIndex = 0,
        selectedModel = SelectModelUiModel(
            id = 1,
            name = "르블랑",
            price = 40000000,
            features = emptyList()
        ),
        shouldShowDialog = true,
        onCarImageClick = {},
        onModelSelect = {},
        onInitialize = {}
    )
}