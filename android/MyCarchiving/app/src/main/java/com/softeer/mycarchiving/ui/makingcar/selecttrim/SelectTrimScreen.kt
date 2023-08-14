package com.softeer.mycarchiving.ui.makingcar.selecttrim

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.MainActivity
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.ui.component.OptionCardForDetail
import com.softeer.mycarchiving.ui.component.OptionNameWithDivider
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel

@Composable
fun SelectTrimRoute(
    modifier: Modifier = Modifier,
    screenProgress: Int,
    selectTrimViewModel: SelectTrimViewModel = hiltViewModel(),
    makingCarViewModel: MakingCarViewModel = hiltViewModel(LocalContext.current as MainActivity),
) {
    val engines by selectTrimViewModel.engines.collectAsStateWithLifecycle()
    val bodyTypes by selectTrimViewModel.bodyTypes.collectAsStateWithLifecycle()
    val wheels by selectTrimViewModel.wheels.collectAsStateWithLifecycle()

    SelectTrimScreen(
        modifier = modifier,
        screenProgress = screenProgress,
        options = when (screenProgress) {
            0 -> engines
            1 -> bodyTypes
            2 -> wheels
            else -> emptyList()
        },
        onOptionSelect = makingCarViewModel::updateSelectedTrimOption
    )
}

@Composable
fun SelectTrimScreen(
    modifier: Modifier,
    screenProgress: Int,
    options: List<TrimOptionUiModel>,
    onOptionSelect: (TrimOptionUiModel) -> Unit,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            imageModel = { options.getOrNull(selectedIndex)?.imageUrl },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds
            ),
            previewPlaceholder = R.drawable.ic_launcher_background,
            component = rememberImageComponent {
                +CrossfadePlugin(
                    duration = 1000
                )
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OptionNameWithDivider(optionName = options.getOrNull(selectedIndex)?.optionName ?: "")
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                options.forEachIndexed { idx, item ->
                    OptionCardForDetail(
                        optionNum = idx + 1,
                        optionName = item.optionName,
                        price = item.price ?: 0,
                        descFirst = if (item.maximumOutput != null) item.optionDesc else null,
                        descSecond = if (item.maximumOutput == null) item.optionDesc else null,
                        maximumTorque = item.maximumTorque,
                        maximumOutput = item.maximumOutput,
                        isSelected = idx == selectedIndex,
                        onClick = {
                            selectedIndex = idx
                            onOptionSelect(item)
                        },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectTrimScreen() {
    SelectTrimScreen(
        modifier = Modifier,
        screenProgress = 0,
        options = listOf(
            TrimOptionUiModel(
                optionName = "디젤 2.2",
                optionDesc = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.",
                price = 1480000,
                maximumOutput = "202/3,800PS/rpm",
                maximumTorque = "45.0/1,750~2,750kgf-m/rpm",
            ),
            TrimOptionUiModel(
                optionName = "가솔린 3.8",
                optionDesc = "고마력의 우수한 가속 성능을 확보하여, 넉넉하고 안정감 있는 주행이 가능합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다.",
                price = 0,
                maximumOutput = "202/3,800PS/rmp",
                maximumTorque = "36.2/5,200kgf-m/rpm",
            )
        ),
        onOptionSelect = {},
    )
}