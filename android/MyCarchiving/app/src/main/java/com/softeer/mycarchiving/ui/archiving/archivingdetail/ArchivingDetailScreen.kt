package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.common.CarDetailsUiModel
import com.softeer.mycarchiving.ui.component.ArchiveBottomBar
import com.softeer.mycarchiving.ui.component.DetailBanner
import com.softeer.mycarchiving.ui.component.DetailReview
import com.softeer.mycarchiving.ui.component.DetailSelectedOption
import com.softeer.mycarchiving.ui.component.DetailTextLabel
import com.softeer.mycarchiving.ui.makingcar.loading.LoadingScreen
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun ArchiveDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ArchiveDetailViewModel = hiltViewModel(),
    onMakingCarClick: (Long?) -> Unit
) {
    val details by viewModel.details.collectAsStateWithLifecycle(initialValue = null)

    ArchiveDetailScreen(
        modifier = modifier,
        details = details,
        onClick = { onMakingCarClick(details?.id) }
    )
}

@Composable
fun ArchiveDetailScreen(
    modifier: Modifier = Modifier,
    details: CarDetailsUiModel?,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(targetState = details, label = "") {
            when (it) {
                null -> LoadingScreen {}
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .background(color = White)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.height(27.dp))
                                DetailTextLabel(text = stringResource(id = R.string.archive_summary_car_info))
                                DetailBanner(
                                    model = it.model,
                                    trim = it.trim,
                                    carImageUrl = it.exteriorColor.carImageUrl ?: "",
                                    price = it.price,
                                    trimOptions = it.trimOptions,
                                    exteriorColor = it.exteriorColor.colorName,
                                    exteriorColorUrl = it.exteriorColor.imageUrl,
                                    interiorColor = it.interiorColor.colorName,
                                    interiorColorUrl = it.interiorColor.imageUrl,
                                )
                                Spacer(modifier = Modifier.height(23.dp))
                                DetailTextLabel(text = stringResource(id = R.string.archive_general_review))
                                Spacer(modifier = Modifier.height(16.dp))
                                DetailReview(review = it.review ?: "")
                                Spacer(modifier = Modifier.height(23.dp))
                                DetailTextLabel(text = stringResource(id = R.string.selected_option))
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                it.selectedOptions?.forEachIndexed { idx, option ->
                                    DetailSelectedOption(
                                        optionImageUrl = option.imageUrl,
                                        optionNum = idx + 1,
                                        optionName = option.name,
                                        subOptions = option.subOptions,
                                        optionReview = option.reviewText,
                                        optionTags = option.tags,
                                    )
                                }
                            }
                        }
                        ArchiveBottomBar(
                            modifier = Modifier.fillMaxWidth(),
                            totalPrice = it.price,
                            onClick = onClick
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewArchiveDetailScreen() {
    ArchiveDetailScreen(
        details = null,
        onClick = {},
    )
}