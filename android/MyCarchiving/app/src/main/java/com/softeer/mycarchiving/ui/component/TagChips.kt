package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.regular14

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

@Preview
@Composable
fun PreviewOptionTagChip() {
    OptionTagChip(tagString = "이것만 있으면 나도 주차고수🚘")
}