package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.medium10
import com.softeer.mycarchiving.ui.theme.medium14

@Composable
fun NavigateBar(
    modifier: Modifier,
    startArea: @Composable () -> Unit,
    centerArea: @Composable () -> Unit,
    endArea: (@Composable () -> Unit)? = null,
    onStartAreaClick: () -> Unit,
    onEndAreaClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = HyundaiSand)
            .padding(end = 15.dp),
    ) {
        Box(
            modifier = modifier
                .size(60.dp)
                .align(Alignment.CenterStart)
                .clickable { onStartAreaClick() },
            contentAlignment = Alignment.Center
        ) {
            startArea()
        }
        Box(
            modifier = modifier
                .align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            centerArea()
        }
        Box(
            modifier = modifier
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
                .clickable { onEndAreaClick?.invoke() },
            contentAlignment = Alignment.Center
        ) {
            endArea?.invoke()
        }
    }
}

@Composable
fun MakeCarNavigateBar(
    modifier: Modifier,
    carName: String,
    onStartAreaClick: () -> Unit,
    onEndAreaClick: () -> Unit,
    isDone: Boolean
) {
    NavigateBar(
        modifier = modifier,
        startArea = {
            Icon(
                painter = if (isDone) {
                    painterResource(id = R.drawable.ic_close)
                } else {
                    painterResource(id = R.drawable.ic_back)
                },
                contentDescription = null
            )
        },
        centerArea = {
            Text(
                text = carName,
                style = medium14
            )
        },
        endArea = {
            if (isDone) {
                MyArchiveButton(modifier = modifier)
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_archive),
                        contentDescription = null
                    )
                    Spacer(modifier = modifier.height(3.dp))
                    Text(
                        text = stringResource(id = R.string.archive),
                        style = medium10,
                    )
                }
            }
        },
        onStartAreaClick = onStartAreaClick,
        onEndAreaClick = onEndAreaClick
    )
}

@Preview
@Composable
fun PreviewMakeCarNavigateBar() {
    MakeCarNavigateBar(modifier = Modifier, carName = "펠리세이드", onStartAreaClick = {}, onEndAreaClick = {}, isDone = false)
}

@Composable
fun ArchiveNavigateBar(
    modifier: Modifier,
    onStartAreaClick: () -> Unit,
    onEndAreaClick: () -> Unit
) {
    NavigateBar(
        modifier = modifier,
        startArea = {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        },
        centerArea = {
             Row {
                 Icon(
                     painter = painterResource(id = R.drawable.ic_hyundai),
                     contentDescription = null,
                     tint = PrimaryBlue
                 )
                 Spacer(modifier = modifier.width(6.dp))
                 Text(
                     text = stringResource(id = R.string.archive),
                     style = medium14
                 )
             }
        },
        endArea = {
            MyArchiveButton(modifier = modifier)
        },
        onStartAreaClick = {
            onStartAreaClick()
        },
        onEndAreaClick = {
            onEndAreaClick()
        }
    )
}

@Preview
@Composable
fun PreviewArchiveNavigateBar() {
    ArchiveNavigateBar(modifier = Modifier, {}, {})
}

@Composable
fun MyArchiveNavigateBar(
    modifier: Modifier,
    onStartAreaClick: () -> Unit,
) {
    NavigateBar(
        modifier = modifier,
        startArea = {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        },
        centerArea = {
            Text(
                text = stringResource(id = R.string.my_archive),
                style = medium14
            )
        },
        onStartAreaClick = {
            onStartAreaClick()
        }
    )
}

@Preview
@Composable
fun PreviewMyArchiveNavigateBar() {
    MyArchiveNavigateBar(modifier = Modifier, {})
}

@Composable
fun ReviewNavigateBar(
    modifier: Modifier,
    onStartAreaClick: () -> Unit,
    onEndAreaClick: () -> Unit,
    isBuyer: Boolean
) {
    NavigateBar(
        modifier = modifier,
        startArea = {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
        },
        centerArea = {
            Text(
                text = if (isBuyer) {
                    stringResource(id = R.string.review_purchase_car)
                } else {
                    stringResource(id = R.string.review_test_drive)
                },
                style = medium14
            )
        },
        endArea = {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )
        },
        onStartAreaClick = {
            onStartAreaClick()
        },
        onEndAreaClick = {
            onEndAreaClick()
        }
    )
}

@Preview
@Composable
fun PreviewReviewNavigateBar() {
    ReviewNavigateBar(modifier = Modifier, {}, {}, isBuyer = false)
}
