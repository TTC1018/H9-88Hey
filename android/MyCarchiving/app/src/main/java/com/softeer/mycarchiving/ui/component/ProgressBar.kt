package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium16
import kotlinx.coroutines.flow.MutableStateFlow

data class ProgressItem(
    val id: Int,
    val name: String,
    val children: List<ProgressItemChild>,
    val needNoChildProgress: Boolean = false
)

data class ProgressItemChild(
    val id: Int,
    val childName: String,
    val fontSize: Int
)

val firstProgress = ProgressItem(
    id = 0,
    name = "트림 선택",
    children = listOf(
        ProgressItemChild(0, "엔진" , 14),
        ProgressItemChild(1, "바디타입", 14),
        ProgressItemChild(2, "구동방식", 14),
    ),
    needNoChildProgress = true
)

val secondProgress = ProgressItem(
    id = 1,
    name = "색상 선택",
    children = listOf(
        ProgressItemChild(0, "외장색상", 14),
        ProgressItemChild(1, "내장색상", 14),
    )
)

val thirdProgress = ProgressItem(
    id = 2,
    name = "옵션 선택",
    children = listOf(
        ProgressItemChild(0, "선택옵션", 10),
        ProgressItemChild(1, "H Genuine Accessories", 10),
        ProgressItemChild(2, "N Performance", 10),
    )
)

val progressItems = listOf(firstProgress, secondProgress, thirdProgress)
val currentProgress = MutableStateFlow(progressItems[0])
val currentProgressChildId = MutableStateFlow(if (progressItems[0].needNoChildProgress) -1 else 0)
val currentProgressChildren = MutableStateFlow(progressItems[0].children)
val remainProgressCountList = MutableStateFlow(mutableListOf(progressItems.size - 1, progressItems.size))
val progressEnd = MutableStateFlow(false)

fun onNextProgress() {
    // 다음 세부 단계가 남았을 경우
    if (currentProgressChildId.value < currentProgress.value.children.size - 1) {
        currentProgressChildId.value = currentProgressChildId.value + 1
        return
    }
    // 다음 단계가 남았을 경우
    val currentId = currentProgress.value.id
    if (currentId < progressItems.size) {
        progressItems[currentId + 1].let {
            currentProgress.value = it
            currentProgressChildren.value = it.children
            currentProgressChildId.value = it.children.first().id
        }
        remainProgressCountList.value = remainProgressCountList.value.also {
            it.removeFirst()
        }
        return
    }
    // 모든 단계가 끝나는 경우
    progressEnd.value = true
}

fun onBackProgress() {
    progressEnd.value = false
    // 이전 세부 단계가 남았을 경우
    val minChildId = if (currentProgress.value.needNoChildProgress) -1 else 0
    if (currentProgressChildId.value > minChildId) {
        currentProgressChildId.value = currentProgressChildId.value - 1
        return
    }
    // 이전 단계가 남았을 경우
    val currentId = currentProgress.value.id
    if (currentId > 0) {
        progressItems[currentId - 1].let {
            currentProgress.value = it
            currentProgressChildren.value = it.children
            currentProgressChildId.value = it.children.last().id
        }
        remainProgressCountList.value = remainProgressCountList.value.also {
            val newProgressCount = if (it.isEmpty()) progressItems.size else it.last() - 1
            it.add(0, newProgressCount)
        }
        return
    }
}

@Composable
fun ProgressBar(
    modifier: Modifier,
    currentProgress: ProgressItem,
    currentChildId: Int,
    currentProgressChildren: List<ProgressItemChild>,
    remainCountList: MutableList<Int>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp)
            .background(HyundaiLightSand)
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onNextProgress() },
                    onLongPress = { onBackProgress() }
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProgressNumberCircle(modifier = modifier, number = currentProgress.id + 1, focus = true)
        Spacer(modifier = modifier.width(5.dp))
        Text(
            text = currentProgress.name,
            style = medium16
        )
        Spacer(modifier = modifier.width(12.dp))
        LazyRow(
            modifier = modifier
                .weight(1f),
            userScrollEnabled = false
        ) {
            items(
                items = currentProgressChildren,
                key = { it.id },
                itemContent = { progressChild ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = progressChild.childName,
                            style = medium14,
                            color = if (progressChild.id == currentChildId) Black else MediumGray,
                            fontSize = progressChild.fontSize.sp
                        )
                        if (progressChild.id < currentProgressChildren.size - 1) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_right),
                                contentDescription = null,
                                colorFilter = if (progressChild.id == currentChildId) {
                                    ColorFilter.tint(Black)
                                } else {
                                    ColorFilter.tint(MediumGray)
                                }
                            )
                        }
                    }
                }
            )
        }
        LazyRow(
            userScrollEnabled = false
        ) {
            items(
                items = remainCountList,
                key = { it },
                itemContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProgressNumberCircle(modifier = modifier, number = it, focus =  false)
                        if (it < remainCountList.last()) {
                            Divider(
                                modifier = modifier
                                    .width(16.dp),
                                thickness = 1.dp,
                                color = MediumGray
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewProgressBar() {
    ProgressBar(
        modifier = Modifier,
        currentProgress = currentProgress.collectAsState().value,
        currentChildId = currentProgressChildId.collectAsState().value,
        currentProgressChildren = currentProgressChildren.collectAsState().value,
        remainCountList = remainProgressCountList.collectAsState().value
    )
}