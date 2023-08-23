package com.softeer.mycarchiving.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.common.ProgressUiModel
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.util.MakeCarProcess

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    makeCarProcess: List<ProgressUiModel>,
    currentProgressId: Int,
    currentChildId: Int,
    isDone: Boolean
) {
    if (!isDone) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(58.dp)
                .background(HyundaiLightSand)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProgressNumberCircle(
                modifier = modifier,
                numberText = (makeCarProcess[currentProgressId].id + 1).toString(),
                color = Black,
            )
            Spacer(modifier = modifier.width(5.dp))
            Text(
                text = makeCarProcess[currentProgressId].name,
                style = medium16
            )
            Spacer(modifier = modifier.width(12.dp))
            LazyRow(
                modifier = modifier
                    .weight(1f),
                userScrollEnabled = false,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(
                    items = makeCarProcess[currentProgressId].children,
                    key = { it.id },
                    itemContent = { progressChild ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val animatedTextColor by animateColorAsState(
                                targetValue = if (progressChild.id == currentChildId) Black else MediumGray,
                                animationSpec = tween(durationMillis = 300),
                                label = ""
                            )
                            Text(
                                text = progressChild.childName,
                                style = medium14,
                                color = animatedTextColor,
                                fontSize = progressChild.fontSize.sp
                            )
                            if (progressChild.id < makeCarProcess[currentProgressId].children.size - 1) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (number in currentProgressId + 2..makeCarProcess.last().id + 1) {
                    ProgressNumberCircle(
                        modifier = modifier,
                        numberText = number.toString(),
                        color = MediumGray,
                    )
                    if (number < makeCarProcess.last().id + 1) {
                        Divider(
                            modifier = modifier
                                .width(16.dp),
                            thickness = 1.dp,
                            color = MediumGray
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProgressBar() {
    ProgressBar(
        makeCarProcess = MakeCarProcess.makeCarProcess,
        currentProgressId = 0,
        currentChildId = 2,
        isDone = false
    )
}
