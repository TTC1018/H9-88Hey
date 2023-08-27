package com.softeer.mycarchiving.ui.myarchive.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.enums.MyArchivePage
import com.softeer.mycarchiving.model.common.CarDetailsUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedSelectedOptionUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.ui.component.DetailBanner
import com.softeer.mycarchiving.ui.component.DetailReview
import com.softeer.mycarchiving.ui.component.DetailSelectedOption
import com.softeer.mycarchiving.ui.component.DetailTextLabel
import com.softeer.mycarchiving.ui.component.MyArchiveDetailBottomBar
import com.softeer.mycarchiving.ui.myarchive.main.MyArchiveMainViewModel
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun MyArchiveDetailRoute(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner?,
    mainViewModel: MyArchiveMainViewModel =
        viewModelStoreOwner?.run { hiltViewModel(this) } ?: hiltViewModel(),
    detailViewModel: MyArchiveDetailViewModel = hiltViewModel(),
    onMakingCarClick: (MainDestination, String?) -> Unit,
) {
    val currentPage by mainViewModel.selectedPage.collectAsStateWithLifecycle()
    val madeCarDetail by mainViewModel.detailCar
    val savedCarDetail by detailViewModel.details.collectAsStateWithLifecycle(initialValue = null)
    val isSaved by detailViewModel.isSaved.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                detailViewModel.saveBookmarkState()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    MyArchiveDetailScreen(
        modifier = modifier,
        currentPage = currentPage,
        detailCar = madeCarDetail!!,
        savedCar = savedCarDetail,
        isSaved = isSaved,
        onMakeClick = { onMakingCarClick(MainDestination.MAKING_CAR, savedCarDetail?.id) },
        onBookmarkClick = detailViewModel::switchBookmarkState,
    )
}

@Composable
fun MyArchiveDetailScreen(
    modifier: Modifier,
    currentPage: MyArchivePage,
    detailCar: ArchiveFeedUiModel,
    savedCar: CarDetailsUiModel?,
    isSaved: Boolean,
    onMakeClick: () -> Unit,
    onBookmarkClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (currentPage) {
            MyArchivePage.MADE -> MyArchiveMadePage(
                modifier = Modifier.weight(1f),
                detailCar = detailCar
            )
            MyArchivePage.SAVED -> MyArchiveSavedPage(
                modifier = Modifier.weight(1f),
                savedCar = savedCar
            )
        }
        MyArchiveDetailBottomBar(
            page = currentPage,
            totalPrice = detailCar.totalPrice,
            isSaved = isSaved,
            onMakeClick = onMakeClick,
            onBookmarkClick = onBookmarkClick
        )
    }
}

@Composable
fun MyArchiveMadePage(
    modifier: Modifier = Modifier,
    detailCar: ArchiveFeedUiModel,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White)
                .padding(start = 16.dp, end = 16.dp, top = 27.dp)
        ) {
            DetailTextLabel(text = stringResource(id = R.string.archive_summary_car_info))
            DetailBanner(
                carImageUrl = detailCar.carImageUrl ?: "",
                model = detailCar.modelName,
                trim = detailCar.trim?.name ?: "",
                trimOptions = detailCar.trimOptions.joinToString(" / ") { it.name },
                price = detailCar.totalPrice,
                exteriorColor = detailCar.exteriorColor?.colorName ?: stringResource(id = R.string.error_no_color),
                exteriorColorUrl = detailCar.exteriorColor?.imageUrl ?: "",
                interiorColor = detailCar.interiorColor?.colorName ?: stringResource(id = R.string.error_no_color),
                interiorColorUrl = detailCar.interiorColor?.imageUrl ?: ""
            )
            Spacer(modifier = Modifier.height(23.dp))
            DetailTextLabel(text = stringResource(id = R.string.selected_option))
        }
        MyArchiveSelectOptionArea(
            selectOptions = detailCar.selectedOptions
        )
    }
}

@Composable
fun MyArchiveSavedPage(
    modifier: Modifier = Modifier,
    savedCar: CarDetailsUiModel?,
) {
    savedCar?.run {
        Column(
            modifier = modifier
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
                    model = model,
                    trim = trim,
                    carImageUrl = exteriorColor?.carImageUrl ?: "",
                    price = price,
                    trimOptions = trimOptions.joinToString(" / "),
                    exteriorColor = exteriorColor?.colorName ?: stringResource(id = R.string.error_no_color),
                    exteriorColorUrl = exteriorColor?.imageUrl ?: "",
                    interiorColor = interiorColor?.colorName ?: stringResource(id = R.string.error_no_color),
                    interiorColorUrl = interiorColor?.imageUrl ?: "",
                )
                Spacer(modifier = Modifier.height(23.dp))
                DetailTextLabel(text = stringResource(id = R.string.archive_general_review))
                Spacer(modifier = Modifier.height(16.dp))
                DetailReview(review = review ?: "")
                Spacer(modifier = Modifier.height(23.dp))
                DetailTextLabel(text = stringResource(id = R.string.selected_option))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                selectedOptions?.forEachIndexed { idx, option ->
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
    }
}

@Composable
fun MyArchiveSelectOptionArea(
    modifier: Modifier = Modifier,
    selectOptions: List<ArchiveFeedSelectedOptionUiModel>
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .background(color = HyundaiLightSand),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        selectOptions.forEachIndexed { idx, option ->
            DetailSelectedOption(
                optionImageUrl = option.imageUrl,
                optionNum = idx + 1,
                optionName = option.name,
                subOptions = option.subOptions,
            )
        }
    }
}
