package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner

data class CarBasicItem(
    val id: Int,
    val name: String,
    val detailItems: List<CarBasicDetailItem>
)

data class CarBasicDetailItem(
    val id: Int,
    val detailName: String,
    val description: String,
    val detailImageUrl: String? = null
)

val detailItems = listOf(
    CarBasicDetailItem(id = 0, detailName = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
    CarBasicDetailItem(id = 1, detailName = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."),
    CarBasicDetailItem(id = 2, detailName = "ISG 시스템", "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다.")
)

val basicItem = CarBasicItem(id = 0, name = "파워트레인 성능", detailItems = detailItems)

@Composable
fun CarBasicItem(
    modifier: Modifier,
    basicItem: CarBasicItem
) {
    var showDetail by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = HyundaiLightSand, shape = roundCorner)
                .padding(all = 12.dp)
                .clickable { showDetail = !showDetail },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = basicItem.name,
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
        if (showDetail) {
            Surface(
                modifier = modifier.padding(top = 12.dp, bottom = 6.dp)
            ) {
                LazyColumn(
                    userScrollEnabled = false,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = basicItem.detailItems,
                        key = { it.id },
                        itemContent = {
                            CarBasicDetailItem(modifier = modifier, detailItem = it)
                        }
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewCarBasicItem() {
    CarBasicItem(modifier = Modifier, basicItem = basicItem)
}

@Composable
fun CarBasicDetailItem(
    modifier: Modifier,
    detailItem: CarBasicDetailItem
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = HyundaiNeutral, shape = roundCorner)
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .width(94.dp)
                .height(55.dp)
                .clip(roundCorner),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = modifier.width(17.dp))
        Text(
            text = detailItem.detailName,
            style = regular14
        )
    }
}

@Preview
@Composable
fun PreviewCarBasicDetailItem() {
    CarBasicDetailItem(modifier = Modifier, detailItem = detailItems[0])
}