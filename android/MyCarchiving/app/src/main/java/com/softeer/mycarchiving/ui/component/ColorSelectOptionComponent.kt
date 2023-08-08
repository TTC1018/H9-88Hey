package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.regular14

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

@Composable
fun OptionTagChip(
    modifier: Modifier = Modifier,
    tagString: String
) {
    AssistChip(
        modifier = modifier,
        onClick = { },
        label = { Text(style = regular14, text = tagString) },
        colors = AssistChipDefaults.assistChipColors(containerColor = HyundaiLightSand),
        shape = RoundedCornerShape(8.dp),
        border = null
    )
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

@Preview
@Composable
fun PreviewOptionTagChip() {
    OptionTagChip(tagString = "이것만 있으면 나도 주차고수🚘")
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