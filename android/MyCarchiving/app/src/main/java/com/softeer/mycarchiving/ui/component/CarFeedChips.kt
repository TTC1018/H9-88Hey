package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.CarFeedType
import com.softeer.mycarchiving.enums.CarFeedType.*
import com.softeer.mycarchiving.ui.theme.AlertPrimary
import com.softeer.mycarchiving.ui.theme.AlertSecondary
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiGold
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiNavy
import com.softeer.mycarchiving.ui.theme.HyundaiNeutral
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.medium10
import com.softeer.mycarchiving.ui.theme.medium12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCornerMedium
import com.softeer.mycarchiving.ui.theme.roundCornerMinimum

@Composable
fun CarFeedDateChip(
    modifier: Modifier = Modifier,
    date: String,
    feedType: CarFeedType,
) {
    Text(
        modifier = modifier
            .background(
                color = if (feedType == TEMP_SAVE) AlertSecondary else HyundaiLightSand,
                shape = roundCornerMedium
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        text = when(feedType) {
            TEMP_SAVE -> stringResource(id = R.string.my_temp_save_date, date)
            MADE -> stringResource(id = R.string.my_make_date, date)
            TEST_DRIVE -> stringResource(id = R.string.archive_test_drive_date, date)
            PURCHASE -> stringResource(id = R.string.archive_purchase_date, date)
        },
        style = medium10,
        color = if (feedType == TEMP_SAVE) AlertPrimary else HyundaiGold
    )
}

@Composable
fun CarFeedOptionChip(
    name: String,
    equalsFilter: Boolean = false
) {
    Surface(
        shape = roundCornerMinimum,
        color = if (!equalsFilter) HyundaiNeutral else HyundaiNavy,
        border = BorderStroke(
            width = if (!equalsFilter) 0.5.dp else 0.dp,
            color = LightGray
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = name,
            style = regular14,
            color = if (!equalsFilter) DarkGray else HyundaiLightSand
        )
    }
}

@Composable
fun SavedCarOptionChip(
    name: String,
) {
    Surface(
        shape = roundCornerMinimum,
        color = HyundaiSand,
        border = BorderStroke(
            width = 0.5.dp,
            color = LightGray
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = name,
            style = regular14
        )
    }
}