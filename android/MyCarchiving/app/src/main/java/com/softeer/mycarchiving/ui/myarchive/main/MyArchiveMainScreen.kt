package com.softeer.mycarchiving.ui.myarchive.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.softeer.mycarchiving.enums.MyArchivePage
import com.softeer.mycarchiving.enums.MyArchivePage.*
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.navigation.MyArchiveDestinations
import com.softeer.mycarchiving.ui.component.ChoiceTab
import com.softeer.mycarchiving.ui.component.DeleteMyArchiveCarDialog
import com.softeer.mycarchiving.ui.myarchive.made.MyArchiveMadeScreen
import com.softeer.mycarchiving.ui.myarchive.save.MyArchiveSaveScreen

@Composable
fun MyArchiveMainRoute(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner?,
    viewModel: MyArchiveMainViewModel =
        viewModelStoreOwner?.run { hiltViewModel(this) } ?: hiltViewModel(),
    moveMadeCarDetail: () -> Unit,
    moveSavedCarDetail: (MyArchivePage?, String, MyArchiveDestinations?) -> Unit,
) {
    val selectedPage by viewModel.selectedPage.collectAsStateWithLifecycle()
    val madeCars = viewModel.madeCarFeedPagingData.collectAsLazyPagingItems()
    val savedCars = viewModel.savedCarFeedPagingData.collectAsLazyPagingItems()
    val showDeleteDialog by viewModel.showDeleteDialog
    val showMoveDialog by viewModel.showMoveDialog
    val wantDeleteCarFeed by viewModel.focusedCarFeed

    MyArchiveMainScreen(
        modifier = modifier,
        page = selectedPage,
        madeCars = madeCars,
        savedCars = savedCars,
        showDeleteDialog = showDeleteDialog,
        showMoveDialog = showMoveDialog,
        focusedCarFeed = wantDeleteCarFeed,
        changePage = viewModel::updateSelectedPage,
        onFeedDetail = viewModel::onFeedDetail,
        moveMadeCarDetail = moveMadeCarDetail,
        moveSavedCarDetail = moveSavedCarDetail,
        deleteCarFeed = viewModel::deleteCarFeed,
        openDeleteDialog = viewModel::openDeleteDialog,
        closeDeleteDialog = viewModel::closeDeleteDialog,
        openMoveDialog = viewModel::openMoveDialog,
        closeMoveDialog = viewModel::closeMoveDialog
    )
}

@Composable
fun MyArchiveMainScreen(
    modifier: Modifier = Modifier,
    page: MyArchivePage,
    madeCars: LazyPagingItems<ArchiveFeedUiModel>,
    savedCars: LazyPagingItems<ArchiveFeedUiModel>,
    showDeleteDialog: Boolean,
    showMoveDialog: Boolean,
    focusedCarFeed: ArchiveFeedUiModel?,
    changePage: (MyArchivePage) -> Unit,
    onFeedDetail: (ArchiveFeedUiModel) -> Unit,
    moveMadeCarDetail: () -> Unit,
    moveSavedCarDetail: (MyArchivePage?, String, MyArchiveDestinations?) -> Unit,
    deleteCarFeed: () -> Unit,
    openDeleteDialog: (ArchiveFeedUiModel) -> Unit,
    closeDeleteDialog: () -> Unit,
    openMoveDialog: (ArchiveFeedUiModel) -> Unit,
    closeMoveDialog: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ChoiceTab(
                selectedPage = page,
                onSelect = changePage
            )
        }
        AnimatedContent(
            targetState = page,
            label = ""
        ) {
            when (it) {
                MADE -> MyArchiveMadeScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    showMoveDialog = showMoveDialog,
                    madeCars = madeCars,
                    focusedCarFeed = focusedCarFeed,
                    onFeedDetail = onFeedDetail,
                    moveDetail = moveMadeCarDetail,
                    openDeleteDialog = openDeleteDialog,
                    openMoveDialog = openMoveDialog,
                    closeMoveDialog = closeMoveDialog
                )

                SAVED -> MyArchiveSaveScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    savedCars = savedCars,
                    onFeedDetail = onFeedDetail,
                    moveDetail = moveSavedCarDetail,
                    openDeleteDialog = openDeleteDialog
                )
            }
        }
    }
    if (showDeleteDialog) {
        DeleteMyArchiveCarDialog(
            onDismissRequest = closeDeleteDialog,
            onDelete = deleteCarFeed,
            carName = "${focusedCarFeed!!.modelName} ${focusedCarFeed.trimName}"
        )
    }
}
