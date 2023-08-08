package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.component.CarImageSelectItem
import com.softeer.mycarchiving.ui.component.OptionCardForModel
import com.softeer.mycarchiving.ui.theme.White

// 임시 모델
data class CarModelUiModel(
    val modelNum: Int,
    val modelName: String,
    val price: Int,
    val expanded: Boolean = false
)

val modelList = listOf(
    CarModelUiModel(
        modelNum = 1,
        modelName = "Le Blanc(르블랑)",
        price = 47720000,
        expanded = true
    ),
    CarModelUiModel(
        modelNum = 2,
        modelName = "Exclusive",
        price = 47720000,
    ),
    CarModelUiModel(
        modelNum = 3,
        modelName = "Prestige",
        price = 47720000,
    ),
    CarModelUiModel(
        modelNum = 4,
        modelName = "Calligraphy",
        price = 47720000,
    ),
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectModelScreen(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .padding(bottom = 20.dp)
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(254.dp),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = modifier.padding(all = 16.dp)
        ) {
            FlowRow(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                for(i in 0 until  4) {
                    CarImageSelectItem(modifier = modifier, onItemClick = {})
                }
            }
            LazyColumn(
                modifier = modifier.padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = modelList,
                    key = { it.modelNum },
                    itemContent = {
                        OptionCardForModel(
                            optionNum = it.modelNum,
                            optionName = it.modelName,
                            price = it.price,
                            isExpanded = it.expanded
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectModelScreen() {
    SelectModelScreen(modifier = Modifier)
}