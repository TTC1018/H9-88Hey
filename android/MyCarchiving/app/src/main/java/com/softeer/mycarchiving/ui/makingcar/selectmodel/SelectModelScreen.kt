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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import com.softeer.mycarchiving.ui.component.CarImageSelectItem
import com.softeer.mycarchiving.ui.component.OptionCardForModel
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun SelectModelRoute(
    modifier: Modifier = Modifier,
    viewModel: SelectModelViewModel = hiltViewModel()
) {
    val carModels = viewModel.carModels.collectAsStateWithLifecycle().value
    val carImages = viewModel.carImageUrls.collectAsStateWithLifecycle().value
    val focusedImageIndex = viewModel.focusedImageIndex.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()
    SelectModelScreen(
        modifier = modifier,
        scrollState = scrollState,
        carModels = carModels,
        carImages = carImages,
        focusedImageIndex = focusedImageIndex,
        onCarImageClick = viewModel::onCarImageClick
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
    onCarImageClick: (Int) -> Unit
) {
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
            imageModel = { carImages[focusedImageIndex] },
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
                for(i in carImages.indices) {
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
fun PreviewSelectModelRoute() {
    SelectModelRoute()
}