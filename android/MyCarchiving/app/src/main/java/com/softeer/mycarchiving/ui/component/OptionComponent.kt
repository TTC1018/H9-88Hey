package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.regular16
import com.softeer.mycarchiving.util.toPriceString

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
            .heightIn(min = if (isExpanded) 200.dp else 70.dp)
            .border(
                border = BorderStroke(if (isExpanded) 1.dp else 0.dp, PrimaryBlue60),
                shape = RoundedCornerShape(8.dp)
            )
            .background(
                color = if (isExpanded) PrimaryBlue10 else HyundaiLightSand,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp),
        verticalArrangement = if (isExpanded) Arrangement.SpaceBetween else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OptionTopTitle(
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
fun OptionTopTitle(
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
        PriceText(
            textColor = textColor,
            price = price
        )
    }
}

@Composable
fun PriceText(
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
fun PreviewExpandedOptionCard() {
    OptionCardForModel(
        optionNum = 1,
        optionName = "Le Blanc(르블랑)",
        price = 47720000,
        isExpanded = true
    )
}

@Preview
@Composable
fun PreviewOptionTopTitle() {
    OptionTopTitle(
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