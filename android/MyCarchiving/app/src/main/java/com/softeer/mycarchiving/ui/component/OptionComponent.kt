package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.PrimaryBlue10
import com.softeer.mycarchiving.ui.theme.PrimaryBlue60
import com.softeer.mycarchiving.ui.theme.bold14
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.regular16
import com.softeer.mycarchiving.util.toPriceString

private val optionCardModifier: (Boolean) -> Modifier = { isExpanded ->
    Modifier
        .heightIn(min = if (isExpanded) 200.dp else 70.dp)
        .background(
            color = if (isExpanded) PrimaryBlue10 else HyundaiLightSand,
            shape = RoundedCornerShape(8.dp)
        )
        .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
}


@Composable
fun OptionCardForModel(
    modifier: Modifier = Modifier,
    optionNum: Int,
    optionName: String,
    price: Int,
    isExpanded: Boolean = false
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isExpanded) Modifier.border(
                    border = BorderStroke(1.dp, PrimaryBlue60),
                    shape = RoundedCornerShape(8.dp)
                )
                else Modifier
            )
            .then(optionCardModifier(isExpanded)),
        verticalArrangement = if (isExpanded) Arrangement.SpaceBetween else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OptionTopTitleForModel(
            optionNum = optionNum,
            optionName = optionName,
            price = price,
            isExpanded = isExpanded
        )
        if (isExpanded) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = PrimaryBlue60
            )
            OptionImages()
        }
    }
}

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
                if (isSelected) Modifier.border(
                    border = BorderStroke(2.dp, PrimaryBlue),
                    shape = RoundedCornerShape(8.dp)
                )
                else Modifier
            )
            .then(optionCardModifier(isSelected))
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
fun OptionTopTitleForModel(
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
        PriceTextForModel(
            textColor = textColor,
            price = price
        )
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
fun PriceTextForModel(
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
            text = price.toPriceString()
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
fun OptionImages(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OptionImageProperty(
            property = stringResource(id = R.string.make_car_wheel),
            icon = painterResource(id = R.drawable.ic_wheel)
        )
        Spacer(modifier = Modifier.width(12.dp))
        OptionImageProperty(
            property = stringResource(id = R.string.make_car_surround),
            icon = painterResource(id = R.drawable.ic_surround)
        )
        OptionImageProperty(
            property = stringResource(id = R.string.make_car_cluster),
            icon = painterResource(id = R.drawable.ic_cluster)
        )
    }
}

@Composable
fun OptionImageProperty(
    modifier: Modifier = Modifier,
    property: String,
    icon: Painter
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = icon,
            colorFilter = ColorFilter.tint(color = PrimaryBlue),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = property,
            color = PrimaryBlue,
            textAlign = TextAlign.Center
        )
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
        Text(
            style = regular12,
            color = PrimaryBlue,
            text = descFirst
        )
    }
    Divider(
        modifier = Modifier.fillMaxWidth(),
        thickness = 1.dp,
        color = PrimaryBlue
    )
    if (descSecond != null) {
        Text(
            style = regular12,
            text = descSecond
        )
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

@Preview(widthDp = 343, heightDp = 72)
@Composable
fun PreviewNormalOptionCard() {
    OptionCardForModel(
        optionNum = 1,
        optionName = "Le Blanc(르블랑)",
        price = 47720000
    )
}

@Preview(widthDp = 343, heightDp = 215)
@Composable
fun PreviewExpandedOptionCardForModel() {
    OptionCardForModel(
        optionNum = 1,
        optionName = "Le Blanc(르블랑)",
        price = 47720000,
        isExpanded = true
    )
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
fun PreviewOptionTopTitle() {
    OptionTopTitleForModel(
        optionNum = 1,
        optionName = "Le Blanc(르블랑)",
        price = 47720000,
        isExpanded = false
    )
}

@Preview
@Composable
fun PreviewOptionImageProperty() {
    OptionImageProperty(
        property = stringResource(id = R.string.make_car_wheel),
        icon = painterResource(id = R.drawable.ic_wheel)
    )
}

@Preview
@Composable
fun PreviewOptionInfoRow() {
    OptionInfoRow(
        optionName = "최고출력",
        optionDesc = "202/3,800PS/rpm"
    )
}