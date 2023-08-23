package com.softeer.mycarchiving.ui.makingcar.selecttrim

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.domain.model.CarDetails
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.TrimOptionUiModel
import com.softeer.mycarchiving.ui.component.OptionCardForDetail
import com.softeer.mycarchiving.ui.component.OptionNameWithDivider
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.makingcar.loading.LoadingScreen
import com.softeer.mycarchiving.util.TRIM_BODY_TYPE
import com.softeer.mycarchiving.util.TRIM_COLOR
import com.softeer.mycarchiving.util.TRIM_DRIVING_SYSTEM
import com.softeer.mycarchiving.util.TRIM_ENGINE
import com.softeer.mycarchiving.util.TRIM_EXTERIOR
import com.softeer.mycarchiving.util.TRIM_SELECT
import com.softeer.mycarchiving.util.fadeInAndOut

@Composable
fun SelectTrimRoute(
    modifier: Modifier = Modifier,
    mainProgress: Int,
    screenProgress: Int,
    viewModelOwner: ViewModelStoreOwner,
    selectTrimViewModel: SelectTrimViewModel = hiltViewModel(),
    makingCarViewModel: MakingCarViewModel = hiltViewModel(viewModelOwner),
) {
    val engines by selectTrimViewModel.engines.collectAsStateWithLifecycle()
    val bodyTypes by selectTrimViewModel.bodyTypes.collectAsStateWithLifecycle()
    val wheels by selectTrimViewModel.wheels.collectAsStateWithLifecycle()
    val selectedTrims by makingCarViewModel.selectedTrim.collectAsStateWithLifecycle()
    val carDetails by makingCarViewModel.carDetails.observeAsState()
    val isArchived = carDetails != null && selectedTrims.isEmpty()

    // 아카이빙에서 넘어왔다면 뷰모델에 데이터 세팅
    if (isArchived) {
        InitArchiveDataEffect(
            carDetails = carDetails,
            engines = engines,
            bodyTypes = bodyTypes,
            wheels = wheels,
            saveTrimOptions = makingCarViewModel::updateSelectedTrimOption
        )
    }

    // 방금 전 상태까지 임시 저장
    LaunchedEffect(screenProgress) {
        if (mainProgress == TRIM_SELECT) {
            makingCarViewModel.saveTempCarInfo()
        }
    }

    SelectTrimScreen(
        modifier = modifier,
        screenProgress = screenProgress,
        options = when (mainProgress to screenProgress) {
            TRIM_SELECT to TRIM_ENGINE -> engines
            TRIM_SELECT to TRIM_BODY_TYPE -> bodyTypes
            TRIM_SELECT to TRIM_DRIVING_SYSTEM, TRIM_COLOR to TRIM_EXTERIOR -> wheels
            else -> emptyList()
        },
        savedTrim = selectedTrims.getOrNull(screenProgress),
        isInitial = selectedTrims.getOrNull(screenProgress) == null,
        isArchived = isArchived,
        onOptionSelect = makingCarViewModel::updateSelectedTrimOption
    )
}

@Composable
private fun InitArchiveDataEffect(
    carDetails: CarDetails?,
    engines: List<TrimOptionUiModel>,
    bodyTypes: List<TrimOptionUiModel>,
    wheels: List<TrimOptionUiModel>,
    saveTrimOptions: (TrimOptionUiModel, Int, Boolean, Boolean) -> Unit,
) {
    LaunchedEffect(engines) {
        carDetails?.run {
            engines.find { it.id == engine.id }
                ?.let { saveTrimOptions(it, TRIM_ENGINE, false, true) }
        }
    }
    LaunchedEffect(bodyTypes) {
        carDetails?.run {
            bodyTypes.find { it.id == bodyType.id }
                ?.let { saveTrimOptions(it, TRIM_BODY_TYPE, false, true) }
        }

    }
    LaunchedEffect(wheels) {
        carDetails?.run {
            wheels.find { it.id == wheelDrive.id }
                ?.let { saveTrimOptions(it, TRIM_DRIVING_SYSTEM, false, true) }
        }
    }
}

@Composable
fun SelectTrimScreen(
    modifier: Modifier,
    screenProgress: Int,
    options: List<TrimOptionUiModel>,
    savedTrim: TrimOptionUiModel?,
    isInitial: Boolean,
    isArchived: Boolean,
    onOptionSelect: (TrimOptionUiModel, progress: Int, initial: Boolean) -> Unit,
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val scrollState = rememberScrollState()

    // 아이템 자동 추가 or 이전 선택 아이템 불러오기
    LaunchedEffect(options, savedTrim) {
        if (isInitial && isArchived.not()) {
            // 처음 화면 갱신되면 첫번째 아이템 선택하기
            selectedIndex = 0
        } else {
            // 이미 선택한 적이 있는 영역이라면 미리 선택한 아이템 선택
            options.indexOfFirst { it.id == savedTrim?.id }
                .takeIf { index -> index >= 0 }
                ?.let { savedIndex ->
                    selectedIndex = savedIndex
                }
        }

        options.getOrNull(selectedIndex)?.let {
            onOptionSelect(it, screenProgress, isInitial)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = options,
            transitionSpec = { fadeInAndOut() },
            label = ""
        ) {
            when {
                it.isEmpty() -> LoadingScreen {}
                else -> {
                    Column(
                        modifier = Modifier
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
                            OptionNameWithDivider(
                                optionName = options.getOrNull(selectedIndex)?.optionName ?: ""
                            )
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
                                            onOptionSelect(item, screenProgress, isInitial)
                                        },
                                    )
                                }
                            }
                        }
                    }
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
                id = 0,
                optionName = "디젤 2.2",
                optionDesc = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.",
                imageUrl = "",
                price = 1480000,
                maximumOutput = "202/3,800PS/rpm",
                maximumTorque = "45.0/1,750~2,750kgf-m/rpm",
            ),
            TrimOptionUiModel(
                id = 1,
                optionName = "가솔린 3.8",
                optionDesc = "고마력의 우수한 가속 성능을 확보하여, 넉넉하고 안정감 있는 주행이 가능합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다.",
                imageUrl = "",
                price = 0,
                maximumOutput = "202/3,800PS/rmp",
                maximumTorque = "36.2/5,200kgf-m/rpm",
            )
        ),
        savedTrim = null,
        isInitial = false,
        isArchived = false,
        onOptionSelect = { _, _, _ -> },
    )
}