package com.softeer.mycarchiving.ui.component

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HyundaiTopBar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    colors: TopAppBarColors,
    onNaviButtonClick:() -> Unit, // 타이틀 좌측 버튼 클릭
    onActionClick: () -> Unit, // 타이틀 우측 버튼 클릭
) {
    // TODO UI 컴포넌트랑 병합
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(id = titleRes)) },
        colors = colors
    )
}