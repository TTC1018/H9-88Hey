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
import com.softeer.mycarchiving.model.common.ProgressChildUiModel
import com.softeer.mycarchiving.model.common.ProgressUiModel
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.util.MakeCarProcess.progressItems
import kotlinx.coroutines.flow.MutableStateFlow

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
    currentProgress: ProgressUiModel,
    currentChildId: Int,
    currentProgressChildren: List<ProgressChildUiModel>,
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
        ProgressNumberCircle(
            modifier = modifier,
            numberText = (currentProgress.id + 1).toString(),
            color = Black,
        )
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
                        ProgressNumberCircle(
                            modifier = modifier,
                            numberText = it.toString(),
                            color = MediumGray,
                        )
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