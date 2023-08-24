package com.softeer.mycarchiving.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.ModelFeatureUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.PrimaryBlue10
import com.softeer.mycarchiving.ui.theme.PrimaryBlue60
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium10
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.regular16
import com.softeer.mycarchiving.util.toPriceString

val optionCardModifier: (Boolean, Boolean) -> Modifier = { descFirstFlag, isExpanded ->
    Modifier
        .animateContentSize()
        .heightIn(min = if (isExpanded && descFirstFlag) 200.dp else if (isExpanded) 140.dp else 70.dp)
        .background(
            color = if (isExpanded) PrimaryBlue10 else HyundaiLightSand,
            shape = RoundedCornerShape(8.dp)
        )
        .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
}

@Composable
fun OptionCardForModel(
    modifier: Modifier = Modifier,
    carModelIndex: Int,
    carModel: SelectModelUiModel,
    isExpanded: Boolean = false,
    isArchived: Boolean = false,
    onClick: () -> Unit,
    onInitialize: () -> Unit,
) {
    var shouldShowDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .clickable { if (isArchived) shouldShowDialog = true else onClick() }
            .fillMaxWidth()
            .then(
                if (isExpanded) Modifier
                    .border(
                        border = BorderStroke(1.dp, PrimaryBlue60),
                        shape = RoundedCornerShape(8.dp)
                    )
                else Modifier
            )
            .then(optionCardModifier(true, isExpanded)),
        verticalArrangement = if (isExpanded) Arrangement.SpaceBetween else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OptionTopTitleForModel(
            carModelIndex = carModelIndex,
            carModel = carModel,
            isExpanded = isExpanded,
        )
        if (isExpanded) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = PrimaryBlue60
            )
            OptionImages(modelFeatures = carModel.features)
        }
    }

    if (isArchived && shouldShowDialog) {
        InitializeCarDialog(
            onDismissRequest = {
                shouldShowDialog = false
            },
            onInitialize = {
                shouldShowDialog = false
                onInitialize()
                onClick()
            }
        )
    }
}

@Composable
fun OptionImageProperty(
    modifier: Modifier = Modifier,
    property: String,
    imageUrl: String,
    placeHolder: Int
) {
    Column(
        modifier = modifier
            .fillMaxHeight(0.5f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .widthIn(max = 40.dp)
                .aspectRatio(1f),
            model = imageUrl,
            contentDescription = "",
            placeholder = painterResource(id = placeHolder)
        )
        Text(
            text = property,
            style = medium10,
            color = PrimaryBlue,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OptionTopTitleForModel(
    modifier: Modifier = Modifier,
    carModelIndex: Int,
    carModel: SelectModelUiModel,
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
            text = stringResource(
                id = R.string.make_car_option_title,
                carModelIndex + 1,
                carModel.name
            )
        )
        PriceTextForModel(
            textColor = textColor,
            price = carModel.price
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
fun OptionImages(
    modifier: Modifier = Modifier,
    modelFeatures: List<ModelFeatureUiModel>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OptionImageProperty(
            modifier = Modifier.weight(1f),
            property = modelFeatures[0].name,
            imageUrl = modelFeatures[0].imageUrl,
            placeHolder = R.drawable.ic_wheel
        )
        OptionImageProperty(
            modifier = Modifier.weight(1f),
            property = modelFeatures[1].name,
            imageUrl = modelFeatures[1].imageUrl,
            placeHolder = R.drawable.ic_surround
        )
        OptionImageProperty(
            modifier = Modifier.weight(1f),
            property = modelFeatures[2].name,
            imageUrl = modelFeatures[2].imageUrl,
            placeHolder = R.drawable.ic_cluster
        )
    }
}

/*@Preview(widthDp = 343, heightDp = 72)
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
}*/

/*@Preview
@Composable
fun PreviewOptionTopTitle() {
    OptionTopTitleForModel(
        optionNum = 1,
        optionName = "Le Blanc(르블랑)",
        price = 47720000,
        isExpanded = false
    )
}*/

/*
@Preview
@Composable
fun PreviewOptionImageProperty() {
    OptionImageProperty(
        property = stringResource(id = R.string.make_car_wheel),
        icon = painterResource(id = R.drawable.ic_wheel)
    )
}*/
