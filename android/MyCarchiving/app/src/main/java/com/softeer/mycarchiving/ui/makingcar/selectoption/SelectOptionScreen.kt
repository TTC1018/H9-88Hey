package com.softeer.mycarchiving.ui.makingcar.selectoption

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.ui.component.CarBasicItemButton
import com.softeer.mycarchiving.ui.component.ExtraOptionCard
import com.softeer.mycarchiving.ui.component.ExtraOptionCards
import com.softeer.mycarchiving.ui.component.OptionSelectItem
import com.softeer.mycarchiving.ui.component.OptionSelectedInfo
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium16

@Composable
fun SelectOptionRoute(
    modifier: Modifier = Modifier,
    viewModel: SelectOptionViewModel = hiltViewModel(),
) {
    val options = viewModel.options.collectAsStateWithLifecycle().value
    val focusedOptionIndex = viewModel.focusedOptionIndex.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()
    SelectOptionScreen(
        modifier = modifier,
        scrollState = scrollState,
        options = options,
        focusedIndex =  focusedOptionIndex,
        focusOption = viewModel::focusOptionItem,
        showBasicItems = viewModel::showBasicItems,
        addOption = viewModel::onAddOption,
    )
}

@Composable
fun SelectOptionScreen(
    modifier: Modifier,
    scrollState: ScrollState,
    options: List<SelectOptionUiModel>,
    focusedIndex: Int,
    focusOption: (Int) -> Unit,
    showBasicItems: () -> Unit,
    addOption: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = White)
            .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 14.dp, bottom = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.make_car_selected_option, options.size),
                    style = medium16,
                    color = DarkGray
                )
                Box(modifier = Modifier.padding(end = 16.dp)) {
                    CarBasicItemButton(onClick = showBasicItems)
                }
            }
            Spacer(modifier = Modifier.height(13.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(
                    items = options,
                    itemContent = { idx, option ->
                        OptionSelectItem(
                            modifier = modifier,
                            option = option,
                            onAddClick = addOption,
                            focus = focusedIndex == idx,
                            onFocus = { focusOption(idx) }
                        )
                    }
                )
            }
        }
        Divider(thickness = 6.dp, color = HyundaiLightSand)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            OptionSelectedInfo(option = options[focusedIndex])
            Spacer(modifier = Modifier.height(12.dp))
            options[focusedIndex].subOptions?.let { 
                if (it.size > 1) {
                    ExtraOptionCards(options = it)
                } else {
                    ExtraOptionCard(option = it[0], optionsSize = 1)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSelectOptionRoute() {
    SelectOptionRoute()
}