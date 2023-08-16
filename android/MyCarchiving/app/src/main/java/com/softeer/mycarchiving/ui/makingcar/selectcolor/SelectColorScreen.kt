package com.softeer.mycarchiving.ui.makingcar.selectcolor

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.softeer.mycarchiving.MainActivity
import com.softeer.data.CarColorType
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.ui.component.CarColorSelectItem
import com.softeer.mycarchiving.ui.component.OptionHeadText
import com.softeer.mycarchiving.ui.component.OptionInfoDivider
import com.softeer.mycarchiving.ui.component.OptionRegular14Text
import com.softeer.mycarchiving.ui.component.OptionSelectedInfo
import com.softeer.mycarchiving.ui.component.RotateCarImage
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand

@Composable
fun SelectColorRoute(
    modifier: Modifier = Modifier,
    screenProgress: Int,
    selectColorViewModel: SelectColorViewModel = hiltViewModel(),
    makingCarViewModel: MakingCarViewModel = hiltViewModel(LocalContext.current as MainActivity)
) {
    val carImageUrls by selectColorViewModel.imageUrls.collectAsStateWithLifecycle()
    val interiorImageUrls by selectColorViewModel.interiorImageUrls.collectAsStateWithLifecycle()
    val category by selectColorViewModel.category.collectAsStateWithLifecycle()
    val topImageIndex by selectColorViewModel.topImageIndex.collectAsStateWithLifecycle()
    val selectedIndex by selectColorViewModel.selectedIndex.collectAsStateWithLifecycle()
    val exteriors by selectColorViewModel.exteriors.collectAsStateWithLifecycle()
    val interiors by selectColorViewModel.interiors.collectAsStateWithLifecycle()
    val selectedColor by makingCarViewModel.selectedColor.collectAsStateWithLifecycle()

    LaunchedEffect(screenProgress) {
        val colorOptions = when (screenProgress) {
            0 -> exteriors
            1 -> interiors
            else -> emptyList()
        }

        val isInitial = selectedColor.getOrNull(screenProgress) == null
        if (isInitial) {
            selectColorViewModel.changeSelectedColor(0) // 화면 처음 진입 시 첫 색상 자동 선택
            makingCarViewModel.updateSelectedColorOption(
                colorOptions.firstOrNull(),
                screenProgress,
                isInitial
            )
        } else {
            colorOptions
                .indexOfFirst { it.optionName == selectedColor.getOrNull(screenProgress)?.optionName }
                .takeIf { index -> index >= 0 }
                ?.let { savedIndex ->
                    selectColorViewModel.changeSelectedColor(savedIndex)
                }
        }

        selectColorViewModel.changeCategory(screenProgress)
    }

    SelectColorScreen(
        modifier = modifier,
        screenProgress = screenProgress,
        topImagePath = when (screenProgress) {
            0 -> carImageUrls.getOrNull(selectedIndex) ?: ""
            1 -> interiorImageUrls.getOrNull(selectedIndex) ?: ""
            else -> ""
        },
        topImageIndex = topImageIndex,
        category = category,
        selectedIndex = selectedIndex,
        colorOptions = when (screenProgress) {
            0 -> exteriors
            1 -> interiors
            else -> emptyList()
        },
        isInitial = selectedColor.getOrNull(screenProgress) == null,
        onLeftClick = { selectColorViewModel.changeTopImageIndex(false) },
        onRightClick = { selectColorViewModel.changeTopImageIndex(true) },
        onColorSelect = selectColorViewModel::changeSelectedColor,
        onSaveColor = makingCarViewModel::updateSelectedColorOption
    )
}

@Composable
fun SelectColorScreen(
    modifier: Modifier,
    screenProgress: Int,
    topImagePath: String,
    topImageIndex: Int,
    category: String,
    selectedIndex: Int,
    colorOptions: List<ColorOptionUiModel>,
    isInitial: Boolean,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onColorSelect: (Int) -> Unit,
    onSaveColor: (ColorOptionUiModel, Int, Boolean) -> Unit,
) {
    val selectedColor = colorOptions.getOrNull(selectedIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (selectedColor != null) {
            SelectColorTopArea(
                screenProgress = screenProgress,
                topImagePath = topImagePath,
                topImageIndex = topImageIndex,
                onLeftClick = onLeftClick,
                onRightClick = onRightClick,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OptionHeadText(optionName = category)
                OptionRegular14Text(optionName = selectedColor.optionName)
            }
            LazyRow {
                itemsIndexed(colorOptions) { idx, item ->
                    CarColorSelectItem(
                        onItemClick = {
                            onColorSelect(idx)
                            onSaveColor(item, screenProgress, isInitial)
                        },
                        imageUrl = item.imageUrl,
                        selected = selectedColor.imageUrl == item.imageUrl,
                    )
                }
            }
            OptionInfoDivider(thickness = 4.dp, color = HyundaiLightSand)
            OptionSelectedInfo(
                optionName = selectedColor.optionName,
                optionTags = selectedColor.tags
            )
        }
        AnimatedVisibility(visible = selectedColor == null) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                text = "네트워크 오류 발생",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SelectColorTopArea(
    modifier: Modifier = Modifier,
    screenProgress: Int,
    topImagePath: String,
    topImageIndex: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
) {
    when (screenProgress) {
        0 -> {
            RotateCarImage(
                modifier = modifier,
                imagePath = topImagePath,
                selectedIndex = topImageIndex,
                onLeftClick = onLeftClick,
                onRightClick = onRightClick,
            )
        }

        1 -> {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(topImagePath)
                    .crossfade(true)
                    .build(),
                contentDescription = ""
            )
        }
    }
}

@Preview(widthDp = 375, heightDp = 646)
@Composable
fun PreviewSelectColorScreen() {
    SelectColorScreen(
        modifier = Modifier,
        screenProgress = 0,
        topImagePath = "",
        topImageIndex = 0,
        category = "외장 색상",
        selectedIndex = 0,
        colorOptions = listOf(
            ColorOptionUiModel(
                category = CarColorType.EXTERIOR,
                optionName = "어비스 블랙펄",
                imageUrl = "",
                price = 0,
                matchingColors = listOf(1, 2, 3),
                tags = listOf(
                    "어린이\uD83E\uDDD2",
                    "이것만 있으면 나도 주차 고수🚘",
                    "편리해요😉",
                    "대형견도 문제 없어요🐶",
                    "큰 짐도 OK💼"
                ),
            )
        ),
        isInitial = false,
        onLeftClick = {},
        onRightClick = {},
        onColorSelect = {},
        onSaveColor = { _, _, _ -> }
    )
}