package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.AlertPrimary
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.MediumGray
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.PrimaryBlue500
import com.softeer.mycarchiving.ui.theme.bold20
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner

@Composable
fun HyundaiLogo(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_hyundai),
            contentDescription = null,
            tint = PrimaryBlue
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "HYUNDAI",
            style = bold20,
            color = PrimaryBlue
        )
    }
}

@Composable
fun AlertMessage(
    modifier: Modifier = Modifier,
    message: String,
) {
    Text(
        modifier = modifier,
        text = message,
        style = regular12,
        color = AlertPrimary
    )
}

@Composable
fun LoginInput(
    modifier: Modifier = Modifier,
    placeHolder: String,
    input: String,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = input,
        onValueChange = onValueChanged,
        placeholder = { Text(text = placeHolder, color = MediumGray) },
        textStyle = regular14,
        shape = roundCorner,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = PrimaryBlue,
            unfocusedBorderColor = LightGray,
            focusedBorderColor = PrimaryBlue500
        )
    )
}



@Preview
@Composable
fun PreviewHyundaiLogo() {
    HyundaiLogo()
}

@Preview
@Composable
fun PreviewAlertMessage() {
    AlertMessage(message = "가입되지 않은 이메일입니다.")
}

@Preview
@Composable
fun PreviewLoginInput() {
    LoginInput(
        placeHolder = "이메일을 입력하세요",
        input = "",
        onValueChanged = {}
    )
}