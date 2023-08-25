package com.softeer.mycarchiving.ui.myarchive.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import com.softeer.mycarchiving.ui.component.ChoiceTab
import com.softeer.mycarchiving.ui.component.DeleteMyArchiveCarDialog
import com.softeer.mycarchiving.ui.myarchive.made.MyArchiveMadeScreen
import com.softeer.mycarchiving.ui.myarchive.save.MyArchiveSaveScreen

const val MY_ARCHIVE_MADE = 0
const val MY_ARCHIVE_SAVE = 1

@Composable
fun MyArchiveMainRoute(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner?,
    viewModel: MyArchiveMainViewModel =
        viewModelStoreOwner?.run { hiltViewModel(this) } ?: hiltViewModel(),
    onMadeCarClick: () -> Unit,
    onSavedCarClick: () -> Unit,
) {
    val selectedIndex by viewModel.selectedIndex
    val madeCars = viewModel.madeCarFeedPagingData.collectAsLazyPagingItems()
    val savedCars = viewModel.savedCarFeedPagingData.collectAsLazyPagingItems()
    val showDeleteDialog by viewModel.showDeleteDialog
    val showMoveDialog by viewModel.showMoveDialog
    val wantDeleteCarFeed by viewModel.focusedCarFeed

    MyArchiveMainScreen(
        modifier = modifier,
        selectedIndex = selectedIndex,
        madeCars = madeCars,
        savedCars = savedCars,
        showDeleteDialog = showDeleteDialog,
        showMoveDialog = showMoveDialog,
        focusedCarFeed = wantDeleteCarFeed,
        onSelect = viewModel::updateSelectedIndex,
        onMadeCarClick = onMadeCarClick,
        onMadeCarDetail = viewModel::onCarDetail,
        deleteCarFeed = viewModel::deleteMadeCarFeed,
        onSavedCarClick = onSavedCarClick,
        onSavedCarDelete = viewModel::deleteSavedCarFeed,
        openDeleteDialog = viewModel::openDeleteDialog,
        closeDeleteDialog = viewModel::closeDeleteDialog,
        openMoveDialog = viewModel::openMoveDialog,
        closeMoveDialog = viewModel::closeMoveDialog
    )
}

@Composable
fun MyArchiveMainScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    madeCars: LazyPagingItems<ArchiveFeedUiModel>,
    savedCars: LazyPagingItems<ArchiveFeedUiModel>,
    showDeleteDialog: Boolean,
    showMoveDialog: Boolean,
    focusedCarFeed: ArchiveFeedUiModel?,
    onSelect: (Int) -> Unit,
    onMadeCarClick: () -> Unit,
    onMadeCarDetail: (ArchiveFeedUiModel) -> Unit,
    deleteCarFeed: () -> Unit,
    onSavedCarClick: () -> Unit,
    onSavedCarDelete: (Int) -> Unit,
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
                selectedIndex = selectedIndex,
                onSelect = onSelect
            )
        }
        AnimatedContent(
            targetState = selectedIndex,
            label = ""
        ) {
            when (it) {
                MY_ARCHIVE_MADE -> MyArchiveMadeScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    showMoveDialog = showMoveDialog,
                    madeCars = madeCars,
                    focusedCarFeed = focusedCarFeed,
                    onDetail = onMadeCarDetail,
                    onClick = onMadeCarClick,
                    openDeleteDialog = openDeleteDialog,
                    openMoveDialog = openMoveDialog,
                    closeMoveDialog = closeMoveDialog
                )

                MY_ARCHIVE_SAVE -> MyArchiveSaveScreen(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    savedCars = savedCars,
                    onClick = onSavedCarClick,
                    onDelete = onSavedCarDelete,
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
