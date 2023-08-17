package com.softeer.mycarchiving.ui.makingcar.selectmodel

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.MainActivity
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import com.softeer.mycarchiving.ui.component.CarImageSelectItem
import com.softeer.mycarchiving.ui.component.OptionCardForModel
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.theme.White

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
    val scrollState = rememberScrollState()

    SelectModelScreen(
        modifier = modifier,
        scrollState = scrollState,
        carModels = carModels,
        carImages = carImages,
        focusedImageIndex = focusedImageIndex,
        onCarImageClick = viewModel::onCarImageClick,
        onModelSelect = sharedViewModel::updateSelectedModelInfo
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
    onCarImageClick: (Int) -> Unit,
    onModelSelect: (SelectModelUiModel) -> Unit,
) {
    LaunchedEffect(carModels) {
        carModels.firstOrNull()?.let { onModelSelect(it) } // 첫번째 모델 자동 선택
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .verticalScroll(scrollState)
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(254.dp),
            imageModel = { carImages.getOrNull(focusedImageIndex) },
            previewPlaceholder = R.drawable.ic_launcher_background,
            component = rememberImageComponent {
                +CrossfadePlugin(
                    duration = 1000
                )
            }
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
                        imageUrl = carImages[i]
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
                        isExpanded = index == 0
                    )
                }
            }
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
                name = "르블랑",
                price = 40000000,
                features = emptyList()
            )
        ),
        carImages = emptyList(),
        focusedImageIndex = 0,
        onCarImageClick = {},
        onModelSelect = {},
    )
}