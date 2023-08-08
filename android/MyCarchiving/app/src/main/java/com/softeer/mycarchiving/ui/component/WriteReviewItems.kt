package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner

@Composable
fun ReviewOptionInfo(
    modifier: Modifier,
    name: String,
    description: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(145.dp)
            .background(color = HyundaiLightSand, shape = roundCorner)
            .border(width = 1.dp, color = HyundaiSand)
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .size(105.dp)
                .clip(roundCorner),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
        Spacer(modifier = modifier.width(9.dp))
        Column(
            modifier = modifier
                .weight(1f)
                .height(105.dp)
        ) {
            Text(
                text = name,
                style = bold18
            )
            Spacer(modifier = modifier.height(4.dp))
            Divider(
                thickness = 1.dp,
                color = Black
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = description,
                style = regular14
            )
        }
    }
}

@Preview
@Composable
fun PreviewReviewOptionInfo() {
    ReviewOptionInfo(
        modifier = Modifier,
        name = "디젤 2.2",
        description = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다."
    )
}

@Composable
fun ReviewTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    isFocus: Boolean
) {

}

@Preview
@Composable
fun PreviewReviewTextField() {
    ReviewTextField(
        modifier = Modifier,
        text = "",
        onValueChange = {},
        isFocus = false
    )
}