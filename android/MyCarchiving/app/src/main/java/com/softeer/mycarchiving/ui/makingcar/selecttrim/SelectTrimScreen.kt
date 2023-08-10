package com.softeer.mycarchiving.ui.makingcar.selecttrim

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.OptionCardUiModel
import com.softeer.mycarchiving.ui.component.OptionCardForDetail
import com.softeer.mycarchiving.ui.component.OptionNameWithDivider

@Composable
fun SelectTrimRoute(
    modifier: Modifier = Modifier,
    selectTrimViewModel: SelectTrimViewModel = hiltViewModel(),
) {
    val options by selectTrimViewModel.options.collectAsStateWithLifecycle()

    SelectTrimScreen(
        modifier = modifier,
        options = options
    )
}

@Composable
fun SelectTrimScreen(
    modifier: Modifier,
    options: List<OptionCardUiModel>,
) {
    var selectedIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        GlideImage(
            modifier = Modifier.fillMaxWidth(),
            imageModel = { options[selectedIndex].imageUrl },
            requestOptions = { RequestOptions().fitCenter() },
            previewPlaceholder = R.drawable.ic_launcher_background
        )
        OptionNameWithDivider(optionName = options[selectedIndex].optionName)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(items = options) { idx, item ->
                OptionCardForDetail(
                    optionNum = idx + 1,
                    optionName = item.optionName,
                    price = item.price ?: 0,
                    descFirst = if (item.maximumOutput != null) item.optionDesc else null,
                    descSecond = if (item.maximumOutput == null) item.optionDesc else null,
                    maximumTorque = item.maximumTorque,
                    maximumOutput = item.maximumOutput,
                    isSelected = idx == selectedIndex,
                    onClick = { selectedIndex = idx },
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectTrimScreen() {
    SelectTrimScreen(
        modifier = Modifier,
        listOf(
            OptionCardUiModel(
                optionName = "디젤 2.2",
                optionDesc = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.",
                price = 1480000,
                maximumOutput = "202/3,800PS/rpm",
                maximumTorque = "45.0/1,750~2,750kgf-m/rpm",
            ),
            OptionCardUiModel(
                optionName = "가솔린 3.8",
                optionDesc = "고마력의 우수한 가속 성능을 확보하여, 넉넉하고 안정감 있는 주행이 가능합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다.",
                price = 0,
                maximumOutput = "202/3,800PS/rmp",
                maximumTorque = "36.2/5,200kgf-m/rpm",
            )
        )
    )
}