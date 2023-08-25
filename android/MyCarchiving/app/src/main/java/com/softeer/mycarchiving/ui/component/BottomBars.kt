package com.softeer.mycarchiving.ui.component

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.navigation.MyArchiveDestinations.*
import com.softeer.mycarchiving.ui.HyundaiAppState
import com.softeer.mycarchiving.ui.makingcar.MakingCarViewModel
import com.softeer.mycarchiving.ui.myarchive.main.MY_ARCHIVE_MADE
import com.softeer.mycarchiving.ui.myarchive.main.MY_ARCHIVE_SAVE
import com.softeer.mycarchiving.ui.rememberHyundaiAppState
import com.softeer.mycarchiving.ui.theme.Black
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.bold18
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.util.toPriceString

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    totalPrice: Int,
    summaryText: String,
    underLineWidth: Int,
    buttonArea: @Composable () -> Unit,
    onShowSummary: (() -> Unit)? = null,
) {
    val animatedPrice by animateIntAsState(targetValue = totalPrice, label = "")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(137.dp)
            .background(HyundaiSand)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 32.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clickable { onShowSummary?.invoke() }
            ) {
                Text(
                    text = summaryText,
                    style = medium12,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier.width(underLineWidth.dp),
                    thickness = 1.5.dp,
                    color = Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = animatedPrice.toPriceString(),
                    style = bold18
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.won),
                    style = medium12
                )
            }
        }
        Spacer(modifier = Modifier.height(13.dp))
        buttonArea()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeCarBottomBar(
    modifier: Modifier = Modifier,
    appState: HyundaiAppState,
    viewModelStoreOwner: ViewModelStoreOwner?,
    viewModel: MakingCarViewModel =
        viewModelStoreOwner?.run { hiltViewModel(this) } ?: hiltViewModel(),
) {
    val destination = appState.currentMakingCarDestinations
    val processEnd = appState.progressEnd
    val totalPrice by viewModel.totalPrice.collectAsStateWithLifecycle()
    val showSummary by viewModel.showSummary.collectAsStateWithLifecycle()
    val selectedModel by viewModel.selectedModelSimple.observeAsState()
    val trimOptions by viewModel.selectedTrimSimple.collectAsStateWithLifecycle()
    val colorOptions by viewModel.selectedColorSimple.collectAsStateWithLifecycle()
    val totalExtraOption by viewModel.selectedOptionSimple.collectAsStateWithLifecycle()

    BottomBar(
        modifier = modifier,
        totalPrice = totalPrice,
        summaryText = stringResource(id = R.string.show_summary),
        underLineWidth = 67,
        buttonArea = {
            HyundaiButton(
                modifier = modifier,
                backgroundColor = PrimaryBlue,
                textColor = HyundaiLightSand,
                text = if (processEnd) {
                    stringResource(id = R.string.purchase_car)
                } else {
                    stringResource(id = R.string.bottom_bar_next_step)
                },
                onClick = {
                    appState.onNextProgress {
                        appState.navigateInMakingCar(destination)
                    }
                }
            )
        },
        onShowSummary = viewModel::openSummary
    )
    if (showSummary) {
        ModalBottomSheet(
            onDismissRequest = viewModel::closeSummary,
            containerColor = White,
            sheetState = SheetState(skipPartiallyExpanded = true),
            windowInsets = WindowInsets(top = 60.dp),
            scrimColor = Color.Transparent
        ) {
            SummaryBottomSheetContent(
                totalPrice = totalPrice,
                modelOption = selectedModel?.let { listOf(it) } ?: emptyList(),
                trimOptions = trimOptions,
                colorOptions = colorOptions,
                extraOptions = totalExtraOption
            )
        }
    }
}

@Composable
fun ArchiveBottomBar(
    modifier: Modifier = Modifier,
    totalPrice: Int,
    onClick: () -> Unit,
) {
    BottomBar(
        modifier = modifier,
        totalPrice = totalPrice,
        summaryText = stringResource(id = R.string.archive_total_price),
        underLineWidth = 35,
        buttonArea = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArchiveSaveButton(onSave = {})
                Spacer(modifier = Modifier.width(16.dp))
                HyundaiButton(
                    backgroundColor = PrimaryBlue,
                    textColor = HyundaiLightSand,
                    text = stringResource(id = R.string.archive_make_my_car),
                    onClick = onClick
                )
            }
        }
    )
}

@Composable
fun MyArchiveBottomBar(
    modifier: Modifier = Modifier,
    totalPrice: Int
) {
    BottomBar(
        modifier = modifier,
        totalPrice = totalPrice,
        summaryText = stringResource(id = R.string.archive_total_price),
        underLineWidth = 35,
        buttonArea = {
            HyundaiButton(
                modifier = modifier,
                backgroundColor = PrimaryBlue,
                textColor = HyundaiLightSand,
                text = stringResource(id = R.string.purchase_car),
                onClick = {/*unused*/}
            )
        }
    )
}

@Composable
fun MyArchiveDetailBottomBar(
    modifier: Modifier = Modifier,
    screenIndex: Int,
    totalPrice: Int
) {
    when (screenIndex) {
        MY_ARCHIVE_MADE -> MyArchiveBottomBar(modifier = modifier , totalPrice = totalPrice)
        MY_ARCHIVE_SAVE -> ArchiveBottomBar(modifier = modifier)
    }
}

@Preview
@Composable
fun PreviewMakeCarBottomBar() {
    MakeCarBottomBar(
        modifier = Modifier,
        appState = rememberHyundaiAppState(),
        viewModelStoreOwner = null,
    )
}

@Preview
@Composable
fun PreviewArchiveBottomBar() {
    ArchiveBottomBar(
        totalPrice = 0,
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewMyArchiveBottomBar() {
    MyArchiveBottomBar(totalPrice = 47700000)
}


