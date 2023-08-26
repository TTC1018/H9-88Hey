package com.softeer.mycarchiving.ui.myarchive.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
        isSaved = isSaved,
        onMakeClick = { onMakingCarClick(MainDestination.MAKING_CAR, madeCarDetail?.id) },
        onBookmarkClick = detailViewModel::switchBookmarkState,
    )
}

@Composable
fun MyArchiveDetailScreen(
    modifier: Modifier,
    currentPage: MyArchivePage,
    detailCar: ArchiveFeedUiModel,
    isSaved: Boolean,
    onMakeClick: () -> Unit,
    onBookmarkClick: () -> Unit,
) {
    Column(
        modifier = modifier
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
                    .fillMaxWidth()
                    .background(color = White)
                    .padding(start = 16.dp, end = 16.dp, top = 27.dp)
            ) {
                DetailTextLabel(text = stringResource(id = R.string.archive_summary_car_info))
                DetailBanner(
                    carImageUrl = detailCar.carImageUrl ?: "",
                    model = detailCar.modelName,
                    trim = detailCar.trimName ?: "",
                    trimOptions = detailCar.trimOptions.joinToString(" / "),
                    price = detailCar.totalPrice,
                    exteriorColor = detailCar.exteriorColor?.name ?: "",
                    exteriorColorUrl = detailCar.exteriorColor?.colorImageUrl ?: "",
                    interiorColor = detailCar.interiorColor?.name ?: "",
                    interiorColorUrl = detailCar.interiorColor?.colorImageUrl ?: ""
                )
                Spacer(modifier = Modifier.height(23.dp))
                if (currentPage == MyArchivePage.SAVED) {
                    DetailTextLabel(text = stringResource(id = R.string.archive_general_review))
                    Spacer(modifier = Modifier.height(16.dp))
                    DetailReview(review = "")
                    Spacer(modifier = Modifier.height(23.dp))
                }
                DetailTextLabel(text = stringResource(id = R.string.selected_option))
            }
            MyArchiveSelectOptionArea(
                selectOptions = detailCar.selectedOptions
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
