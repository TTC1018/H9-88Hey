package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.common.CarBasicDetailUiModel
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner

val detailItem = CarBasicDetailUiModel(
    id = 0,
    detailName = "ISG 시스템",
    "신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는 시스템입니다."
)

@Composable
fun BasicItemDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    detailItem: CarBasicDetailUiModel
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            color = White,
            shape = roundCorner
        ) {
            Column(
                modifier = modifier
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = modifier
                            .align(Alignment.Center),
                        text = detailItem.detailName,
                        style = medium18,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        modifier = modifier
                            .align(Alignment.CenterEnd)
                            .clickable { onDismissRequest() },
                        painter = painterResource(id = R.drawable.ic_close_light),
                        contentDescription = null
                    )
                }
                Spacer(modifier = modifier.height(12.dp))
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(161.dp)
                        .clip(roundCorner),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    modifier = modifier
                        .fillMaxWidth(),
                    text = detailItem.description,
                    style = regular12
                )
            }
        }
    }
}

@Composable
fun ButtonDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    description: String,
    annotatedTargetStartIndex: Int,
    annotatedTargetLength: Int,
    buttonArea: @Composable () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append(description.substring(0, annotatedTargetStartIndex))
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(description.substring(annotatedTargetStartIndex, annotatedTargetStartIndex + annotatedTargetLength))
        }
        append(description.substring(annotatedTargetStartIndex + annotatedTargetLength))
    }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = modifier.height(186.dp),
            color = White,
            shape = roundCorner
        ) {
            Column(
                modifier = modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 13.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = modifier,
                        text = annotatedString,
                        style = regular14,
                        textAlign = TextAlign.Center
                    )
                }
                buttonArea()
            }
        }
    }
}

@Composable
fun FinishMakeCarDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onFinish: () -> Unit
) {
    ButtonDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        description = stringResource(id = R.string.make_car_dialog_description),
        annotatedTargetStartIndex = 24,
        annotatedTargetLength = 16
    ) {
        Row {
            HyundaiButton(
                modifier = modifier.width(120.dp),
                backgroundColor = LightGray,
                textColor = DarkGray,
                text = stringResource(id = R.string.cancel),
                onClick = onDismissRequest
            )
            Spacer(modifier = modifier.width(6.dp))
            HyundaiButton(
                modifier = modifier,
                backgroundColor = PrimaryBlue,
                textColor = White,
                text = stringResource(id = R.string.make_car_dialog_finish),
                onClick = onFinish
            )
        }
    }
}



@Composable
fun DeleteMadeCarDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onDelete: () -> Unit,
    carName: String
) {
    ButtonDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        description = stringResource(id = R.string.my_dialog_delete_description, carName),
        annotatedTargetStartIndex = 0,
        annotatedTargetLength = carName.length
    ) {
        Row {
            HyundaiButton(
                modifier = modifier.weight(1f),
                backgroundColor = LightGray,
                textColor = DarkGray,
                text = stringResource(id = R.string.cancel),
                onClick = onDismissRequest
            )
            Spacer(modifier = modifier.width(6.dp))
            HyundaiButton(
                modifier = modifier.weight(1f),
                backgroundColor = PrimaryBlue,
                textColor = White,
                text = stringResource(id = R.string.my_dialog_delete),
                onClick = onDelete
            )
        }
    }
}

@Composable
fun MoveMakeCarDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onMove: () -> Unit,
    saveDate: String
) {
    ButtonDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        description = stringResource(id = R.string.my_dialog_continue_make_description, saveDate),
        annotatedTargetStartIndex = 0,
        annotatedTargetLength = saveDate.length
    ) {
        Row {
            HyundaiButton(
                modifier = modifier.width(120.dp),
                backgroundColor = LightGray,
                textColor = DarkGray,
                text = stringResource(id = R.string.cancel),
                onClick = onDismissRequest
            )
            Spacer(modifier = modifier.width(6.dp))
            HyundaiButton(
                modifier = modifier,
                backgroundColor = PrimaryBlue,
                textColor = White,
                text = stringResource(id = R.string.my_dialog_continue_make),
                onClick = onMove
            )
        }
    }
}

@Preview
@Composable
fun PreviewBasicItemDialog() {
    BasicItemDialog(modifier = Modifier, onDismissRequest = {}, detailItem = detailItem)
}

@Preview
@Composable
fun PreviewFinishMakeCarDialog() {
    FinishMakeCarDialog(modifier = Modifier, onDismissRequest = {}, onFinish = {})
}

@Preview
@Composable
fun PreviewDeleteMadeCarDialog() {
    DeleteMadeCarDialog(
        modifier = Modifier,
        onDismissRequest = {},
        onDelete = {},
        carName = "펠리세이드 Le Blanc"
    )
}

@Preview
@Composable
fun PreviewMoveMakeCarDialog() {
    MoveMakeCarDialog(
        modifier = Modifier,
        onDismissRequest = {},
        onMove = {},
        saveDate = "23년 7월 18일"
    )
}