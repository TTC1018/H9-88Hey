package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiActiveBlue
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.PrimaryBlue10
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner

@Composable
fun OptionSelectItem(
    modifier: Modifier,
    optionName: String,
    optionPrice: String,
    onItemClick: () -> Unit,
    onAddClick: () -> Unit
) {
    var itemSelect by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier
            .width(160.dp)
            .height(197.dp)
            .clickable {
                itemSelect = !itemSelect
                onItemClick()
            },
        shape = roundCorner,
        border = if (itemSelect) BorderStroke(width = 2.dp, color = PrimaryBlue) else null,
        color = if (itemSelect) PrimaryBlue10 else HyundaiLightSand
    ) {
        Column {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(93.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 9.dp, end = 9.dp, top = 10.dp, bottom = 3.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = optionName,
                        style = medium14,
                        color = if (itemSelect) PrimaryBlue else Black
                    )
                    Spacer(modifier = modifier.height(5.dp))
                    Text(
                        modifier = modifier
                            .align(Alignment.End),
                        text = stringResource(id = R.string.plus_space_price_won, optionPrice),
                        style = medium14,
                        color = if (itemSelect) PrimaryBlue else Black
                    )
                }
                OptionAddButton(modifier = modifier, onClick = onAddClick)
            }
        }
    }
}

@Preview
@Composable
fun PreviewOptionSelectItem() {
    OptionSelectItem(modifier = Modifier, optionName = "컴포트 2", optionPrice = "1,090,000", onItemClick = {}, onAddClick = {})
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OptionSelectedInfo(
    optionTags: List<String>
) {
    OptionHeadText(optionName = "컴포트 II")
    OptionHeadComment()
    FlowRow(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        optionTags.forEach { tagString ->
            OptionTagChip(tagString = tagString)
        }
    }
}

@Preview(heightDp = 140)
@Composable
fun PreviewOptionSelectedInfo() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OptionSelectedInfo(
            optionTags = listOf(
                "어린이🧒",
                "안전사고 예방🚨",
                "대형견도 문제 없어요🐶",
                "가족들도 좋은 옵션👨‍👩‍👧‍👦"
            )
        )
    }
}


@Preview(heightDp = 60)
@Composable
fun PreviewOptionColorNameSentence() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OptionHeadText(optionName = "퀄팅 천연(블랙)")
        OptionHeadComment()
    }
}