package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.AlertPrimary
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNavy
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.ui.theme.roundCornerSmall

@Composable
fun HyundaiButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = PrimaryBlue,
    textColor: Color = HyundaiLightSand,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        onClick = onClick,
        shape = roundCorner
    ) {
        Text(
            text = text,
            style = bold18,
            color = textColor
        )
    }
}

@Composable
fun MyArchiveButton(
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .background(color = DarkGray, shape = roundCornerSmall)
            .padding(all = 8.dp),
    ) {
        Text(
            text = stringResource(id = R.string.my_archive),
            style = medium14,
            color = HyundaiLightSand
        )
    }
}

@Composable
fun CarBasicItemButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .background(color = HyundaiSand, shape = roundCornerSmall)
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = stringResource(id = R.string.make_car_basic_item),
        style = regular14
    )
}

@Composable
fun OptionAddButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    var isSelect by remember { mutableStateOf(false) }
    Button(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = if (isSelect) {
            ButtonDefaults.buttonColors(containerColor = HyundaiNavy, contentColor = HyundaiNeutral)
        } else {
            ButtonDefaults.buttonColors(containerColor = HyundaiNeutral, contentColor = HyundaiNavy)
        },
        border = if (isSelect) null else BorderStroke(width = 1.dp, color = HyundaiNavy),
        onClick = {
            isSelect = !isSelect
            onClick()
        },
        shape = roundCorner,
    ) {
        Text(
            text = if (isSelect) stringResource(id = R.string.make_car_add_done) else stringResource(id = R.string.make_car_add),
            style = medium14,
            color = if (isSelect) HyundaiNeutral else HyundaiNavy
        )
        if (isSelect) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SearchConditionButton(
    modifier: Modifier = Modifier,
    selectedCar: String? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = HyundaiLightSand, shape = roundCorner)
            .padding(all = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text =  selectedCar ?: stringResource(id = R.string.archive_search_select_option),
            style = if (selectedCar != null) medium14 else regular14,
            color = if (selectedCar != null) Black else MediumGray
        )
        Icon(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_right),
            contentDescription = null,
            tint = DarkGray
        )
    }
}

@Composable
fun SearchConditionChip(
    modifier: Modifier,
    name: String,
    onClick: () -> Unit
) {
    var isSelect by remember { mutableStateOf(false) }
    Text(
        modifier = modifier
            .clickable {
                isSelect = !isSelect
                onClick()
            }
            .background(
                color = if (isSelect) HyundaiNavy else HyundaiNeutral,
                shape = roundCornerSmall
            )
            .border(
                width = if (isSelect) 0.dp else 0.5.dp,
                color = if (isSelect) HyundaiNavy else LightGray
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = name,
        style = regular14,
        color = if (isSelect) HyundaiLightSand else DarkGray
    )
}

@Composable
fun SearchConditionChipForDelete(
    modifier: Modifier = Modifier,
    name: String,
    onDelete: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = HyundaiNavy, shape = roundCornerSmall)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = regular14,
            color = HyundaiLightSand
        )
        Spacer(modifier = modifier.width(10.dp))
        Icon(
            modifier = modifier
                .size(12.dp)
                .clickable { onDelete() },
            painter = painterResource(id = R.drawable.ic_close_chip),
            contentDescription = null,
            tint = LightGray
        )
    }
}

@Composable
fun ArchiveSaveButton(
    modifier: Modifier,
    onSave: () -> Unit
) {
    var isSaved by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .width(52.dp)
            .height(52.dp)
            .background(
                color = PrimaryBlue,
                shape = CircleShape
            )
            .clickable {
                isSaved = !isSaved
                onSave()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = null,
            tint = if (isSaved) AlertPrimary else White
        )
    }
}

@Preview
@Composable
fun PreviewAppButton() {
    val pressed: () -> Unit = {}
    HyundaiButton(
        modifier = Modifier,
        backgroundColor = PrimaryBlue,
        textColor = HyundaiSand,
        text = "다음 단계로",
        onClick = pressed
    )
}

@Preview
@Composable
fun PreviewMyArchiveButton() {
    MyArchiveButton(modifier = Modifier)
}

@Preview
@Composable
fun PreviewCarBasicItemButton() {
    CarBasicItemButton(onClick = {})
}

@Preview
@Composable
fun PreviewOptionAddButton() {
    OptionAddButton(modifier = Modifier, onClick = {})
}

@Preview
@Composable
fun PreviewSearchConditionButton() {
    SearchConditionButton(modifier = Modifier, selectedCar = "펠리세이드", {})
}

@Preview
@Composable
fun PreviewSearchConditionChip() {
    SearchConditionChip(modifier = Modifier, name = "펠리세이드", {})
}

@Preview
@Composable
fun PreviewSearchConditionChipForDelete() {
    SearchConditionChipForDelete(modifier = Modifier, name = "듀얼 와이드 선루프", {})
}

@Preview
@Composable
fun PreviewArchiveSaveButton() {
    ArchiveSaveButton(modifier = Modifier, onSave = {})
}


