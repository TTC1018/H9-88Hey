package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.bold14
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.regular16
import com.softeer.mycarchiving.util.toPriceString

@Composable
fun OptionCardForDetail(
    modifier: Modifier = Modifier,
    optionNum: Int,
    optionName: String,
    price: Int,
    descFirst: String? = null,
    descSecond: String? = null,
    maximumOutput: String? = null,
    maximumTorque: String? = null,
) {
    var isSelected by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isSelected) Modifier
                    .border(
                        border = BorderStroke(2.dp, PrimaryBlue),
                        shape = RoundedCornerShape(8.dp)
                    )
                else Modifier
            )
            .then(optionCardModifier(descFirst != null, isSelected))
            .clickable { isSelected = isSelected.not() },
        verticalArrangement = if (isSelected) Arrangement.SpaceBetween else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OptionTopTitleForDetail(
            optionNum = optionNum,
            optionName = optionName,
            price = price,
            isExpanded = isSelected
        )
        if (isSelected) {
            OptionSelectedDetail(
                descFirst = descFirst,
                descSecond = descSecond,
                maximumOutput = maximumOutput,
                maximumTorque = maximumTorque,
            )
        }
    }
}

@Composable
fun OptionTopTitleForDetail(
    modifier: Modifier = Modifier,
    optionNum: Int,
    optionName: String,
    price: Int,
    isExpanded: Boolean
) {
    val textColor = if (isExpanded) PrimaryBlue else Black

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            style = bold18,
            color = textColor,
            text = stringResource(id = R.string.make_car_option_title, optionNum, optionName)
        )
        PriceTextForDetail(
            textColor = textColor,
            price = price
        )
    }
}

@Composable
fun PriceTextForDetail(
    modifier: Modifier = Modifier,
    textColor: Color,
    price: Int
) {
    Row(
        modifier = modifier
    ) {
        Text(
            style = bold18,
            color = textColor,
            text = stringResource(id = R.string.make_car_price, price.toPriceString())
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            style = regular16,
            color = textColor,
            text = stringResource(id = R.string.make_car_price_tail)
        )
    }
}

@Composable
fun OptionNameWithDivider(
    modifier: Modifier = Modifier,
    optionName: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OptionNameSentence(optionName = optionName)
        OptionInfoDivider(thickness = 4.dp, color = DarkGray)
    }
}

@Composable
fun OptionSelectedDetail(
    descFirst: String?,
    descSecond: String?,
    maximumOutput: String?,
    maximumTorque: String?,
) {
    if (descFirst != null) {
        OptionInfoDescText(text = descFirst)
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = PrimaryBlue
    )
    if (descSecond != null) {
        OptionInfoDescText(text = descSecond)
    }
    if (maximumOutput != null) {
        OptionInfoRow(
            optionName = stringResource(id = R.string.make_car_peak_output),
            optionDesc = maximumOutput
        )
    }
    if (maximumTorque != null) {
        OptionInfoRow(
            optionName = stringResource(id = R.string.make_car_max_torque),
            optionDesc = maximumTorque
        )
    }
}

@Composable
fun OptionInfoDescText(
    text: String
) {
    Text(
        style = regular12,
        color = PrimaryBlue,
        text = text
    )
}

@Composable
fun OptionInfoRow(
    modifier: Modifier = Modifier,
    optionName: String,
    optionDesc: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            style = bold14,
            color = PrimaryBlue,
            text = optionName
        )
        Text(
            style = regular14,
            color = PrimaryBlue,
            text = optionDesc
        )
    }
}

@Composable
fun OptionNameSentence(
    modifier: Modifier = Modifier,
    optionName: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OptionHeadText(
            modifier = Modifier.alignByBaseline(),
            optionName = optionName
        )
        OptionHeadComment(
            modifier = Modifier.alignByBaseline()
        )
    }
}

@Preview(widthDp = 343, heightDp = 186)
@Composable
fun PreviewSelectedOptionCardForDetail() {
    OptionCardForDetail(
        optionNum = 1,
        optionName = "디젤 2.2",
        price = 1480000,
        descFirst = "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.",
        maximumOutput = "202/3,800PS/rpm",
        maximumTorque = "45.0/1,750~2,750kgf-m/rpm"
    )
}

@Preview
@Composable
fun PreviewOptionNameWithDivider() {
    OptionNameWithDivider(optionName = "디젤 2.2")
}

@Preview
@Composable
fun PreviewOptionInfoRow() {
    OptionInfoRow(
        optionName = "최고출력",
        optionDesc = "202/3,800PS/rpm"
    )
}

@Preview
@Composable
fun PreviewOptionNameSentence() {
    OptionNameSentence(
        optionName = "디젤 2.2"
    )
}
