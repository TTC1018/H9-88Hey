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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.medium10
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.util.MakeCarProcess

@Composable
fun HyundaiTopBar(
    appState: HyundaiAppState,
) {
    when(val destination = appState.currentMainDestination) {
        MainDestination.ARCHIVING -> ArchiveNavigateBar(
            onStartAreaClick = appState.navController::popBackStack,
            onEndAreaClick = { appState.navigateToMainDestination(MainDestination.MY_ARCHIVING) }
        )

        MainDestination.MY_ARCHIVING -> MyArchiveNavigateBar(
            onStartAreaClick = appState.navController::popBackStack
        )

        MainDestination.MAKING_CAR -> MakeCarTopBar(appState = appState)

        MainDestination.DRIVER_COMMENT,
        MainDestination.CONSUMER_COMMENT -> ReviewNavigateBar(
            onStartAreaClick = appState.navController::popBackStack,
            onEndAreaClick = { /*앱 종료*/ },
            isBuyer = destination == MainDestination.CONSUMER_COMMENT
        )

        else -> @Composable {}
    }
}

@Composable
fun MakeCarTopBar(
    appState: HyundaiAppState,
    viewModel: MakingCarViewModel = hiltViewModel(),
) {
    val destination = appState.currentMakingCarDestinations
    val selectedCarModel by viewModel.selectedCarName.collectAsStateWithLifecycle()
    val currentProgressId by appState.currentProgressId.collectAsStateWithLifecycle()
    val currentProgressChildId by appState.currentProgressChildId.collectAsStateWithLifecycle()
    val processEnd by appState.progressEnd.collectAsStateWithLifecycle()
    Column {
        MakeCarNavigateBar(
            carName = selectedCarModel,
            onStartAreaClick = appState::onBackProgress,
            onEndAreaClick = { appState.navigateInMakingCar(currentMakingCarDestination = destination) },
            isDone = processEnd
        )
        ProgressBar(
            makeCarProcess = MakeCarProcess.makeCarProcess,
            currentProgressId = currentProgressId,
            currentChildId = currentProgressChildId,
            isDone = processEnd
        )
    }
}

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
    modifier: Modifier = Modifier,
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

@Composable
fun ArchiveNavigateBar(
    modifier: Modifier = Modifier,
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

@Composable
fun MyArchiveNavigateBar(
    modifier: Modifier = Modifier,
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

@Composable
fun ReviewNavigateBar(
    modifier: Modifier = Modifier,
    onStartAreaClick: () -> Unit,
    onEndAreaClick: () -> Unit,
    isBuyer: Boolean,
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
fun PreviewMakeCarNavigateBar() {
    MakeCarNavigateBar(modifier = Modifier, carName = "펠리세이드", onStartAreaClick = {}, onEndAreaClick = {}, isDone = false)
}

@Preview
@Composable
fun PreviewArchiveNavigateBar() {
    ArchiveNavigateBar(modifier = Modifier, {}, {})
}

@Preview
@Composable
fun PreviewMyArchiveNavigateBar() {
    MyArchiveNavigateBar(modifier = Modifier, {})
}

@Preview
@Composable
fun PreviewReviewNavigateBar() {
    ReviewNavigateBar(modifier = Modifier, {}, {}, isBuyer = false)
}
