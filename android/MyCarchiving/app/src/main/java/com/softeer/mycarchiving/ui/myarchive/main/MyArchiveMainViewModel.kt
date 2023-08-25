package com.softeer.mycarchiving.ui.myarchive.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.usecase.myarchive.AddBookmarkUseCase
import com.softeer.domain.usecase.myarchive.CheckBookmarkedUseCase
import com.softeer.domain.usecase.myarchive.DeleteBookmarkUseCase
import com.softeer.domain.usecase.myarchive.DeleteMadeCarFeedUseCase
import com.softeer.domain.usecase.myarchive.GetMadeCarFeedUseCase
import com.softeer.domain.usecase.myarchive.GetSavedCarFeedsUseCase
import com.softeer.mycarchiving.enums.MyArchivePage
import com.softeer.mycarchiving.enums.MyArchivePage.*
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MyArchiveMainViewModel @Inject constructor(
    getMadeCarFeedUseCase: GetMadeCarFeedUseCase,
    getSavedCarFeedsUseCase: GetSavedCarFeedsUseCase,
    private val deleteMadeCarFeedUseCase: DeleteMadeCarFeedUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    private val checkBookmarkedUseCase: CheckBookmarkedUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
): ViewModel() {

    private val _selectedPage = MutableStateFlow(MADE)
    val selectedPage: StateFlow<MyArchivePage> = _selectedPage

    private val refreshMadeCarFeed = MutableStateFlow(true)

    val madeCarFeedPagingData = combine(
        flow = refreshMadeCarFeed,
        flow2 = _selectedPage
    ) { needRefresh, page ->
        if (needRefresh && page == MADE) {
            getMadeCarFeedUseCase()
        } else {
            flowOf(PagingData.empty())
        }
    }.flatMapLatest { it }
        .map { pagingData -> pagingData.map(MyArchiveFeed::asUiModel) }
        .cachedIn(viewModelScope)

    private val refreshSavedCarFeed = MutableStateFlow(true)

    val savedCarFeedPagingData = combine(
        flow = refreshSavedCarFeed,
        flow2 = _selectedPage
    ) { needRefresh, page ->
        if (needRefresh && page == SAVED) {
            getSavedCarFeedsUseCase()
        } else {
            flowOf(PagingData.empty())
        }
    }.flatMapLatest { it }
        .map { pagingData -> pagingData.map(MyArchiveFeed::asUiModel) }
        .cachedIn(viewModelScope)

    private val _detailCar = mutableStateOf<ArchiveFeedUiModel?>(null)
    val detailCar: State<ArchiveFeedUiModel?> = _detailCar

    val showDeleteDialog = mutableStateOf(false)
    val showMoveDialog = mutableStateOf(false)
    val focusedCarFeed = mutableStateOf<ArchiveFeedUiModel?>(null)

    fun updateSelectedPage(page: MyArchivePage) {
        _selectedPage.value = page
    }

    fun onFeedDetail(madeCar: ArchiveFeedUiModel) {
        _detailCar.value = madeCar
    }

    fun openDeleteDialog(feed: ArchiveFeedUiModel) {
        focusedCarFeed.value = feed
        showDeleteDialog.value = true
    }

    fun closeDeleteDialog() {
        showDeleteDialog.value = false
    }

    fun openMoveDialog(feed: ArchiveFeedUiModel) {
        focusedCarFeed.value = feed
        showMoveDialog.value = true
    }

    fun closeMoveDialog() {
        showMoveDialog.value = false
    }

    fun deleteCarFeed() {
        viewModelScope.launch {
            when(_selectedPage.value) {
                MADE -> {
                    val isSuccess = deleteMadeCarFeedUseCase(focusedCarFeed.value!!.id)
                    if (isSuccess) {
                        refreshMadeCarFeed.value = false
                        refreshMadeCarFeed.value = true
                    }
                }
                SAVED -> {
                    val isSuccess = deleteBookmarkUseCase(focusedCarFeed.value!!.id)
                    if (isSuccess) {
                        refreshSavedCarFeed.value = false
                        refreshSavedCarFeed.value = true
                    }
                }
            }
        }
    }
}