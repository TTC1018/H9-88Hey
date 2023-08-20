package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.LightGray

@Composable
fun ChoiceTab(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .background(HyundaiLightSand)
            .heightIn(max = 85.dp)
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = HyundaiNeutral, shape = RoundedCornerShape(30.dp))
                .border(
                    width = 1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(5.dp)
        ) {
            MyArchiveChoiceButton(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .fillMaxHeight()
                    .zIndex(if (selectedIndex == 0) 1f else 0f)
                    .align(Alignment.CenterStart),
                text = stringResource(id = R.string.my_make_car_list),
                isSelected = selectedIndex == 0,
                onClick = { onSelect(0) },
            )
            MyArchiveChoiceButton(
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .fillMaxHeight()
                    .zIndex(if (selectedIndex == 1) 1f else 0f)
                    .align(Alignment.CenterEnd),
                text = stringResource(id = R.string.my_save_car_list),
                isSelected = selectedIndex == 1,
                onClick = { onSelect(1) },
            )
        }
    }
}

@Preview(widthDp = 375, heightDp = 87)
@Composable
fun PreviewChoiceTab() {
    ChoiceTab(
        selectedIndex = 0,
        onSelect = {},
    )
}