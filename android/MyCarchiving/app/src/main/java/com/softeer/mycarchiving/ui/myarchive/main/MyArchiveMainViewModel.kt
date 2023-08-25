package com.softeer.mycarchiving.ui.myarchive.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.softeer.domain.model.MyArchiveFeed
import com.softeer.domain.usecase.myarchive.DeleteMadeCarFeedUseCase
import com.softeer.domain.usecase.myarchive.GetMadeCarFeedUseCase
import com.softeer.domain.usecase.myarchive.GetSavedCarFeedsUseCase
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MyArchiveMainViewModel @Inject constructor(
    getMadeCarFeedUseCase: GetMadeCarFeedUseCase,
    getSavedCarFeedsUseCase: GetSavedCarFeedsUseCase,
    private val deleteMadeCarFeedUseCase: DeleteMadeCarFeedUseCase,
): ViewModel() {

    private val _selectedIndex = mutableIntStateOf(0)
    val selectedIndex: State<Int> = _selectedIndex

    private val refreshMadeCarFeed = MutableStateFlow(true)

    val madeCarFeedPagingData = refreshMadeCarFeed.flatMapLatest { needRefresh ->
        if (needRefresh) getMadeCarFeedUseCase()
        else flow {  }
    }.map { pagingData -> pagingData.map(MyArchiveFeed::asUiModel) }
        .cachedIn(viewModelScope)

    private val refreshSavedCarFeed = MutableStateFlow(true)

    val savedCarFeedPagingData = refreshSavedCarFeed.flatMapLatest { needRefresh ->
        if (needRefresh) getSavedCarFeedsUseCase()
        else flow {  }
    }.map { pagingData -> pagingData.map(MyArchiveFeed::asUiModel) }
        .cachedIn(viewModelScope)

    private val _detailCar = mutableStateOf<ArchiveFeedUiModel?>(null)
    val detailCar: State<ArchiveFeedUiModel?> = _detailCar

    val showDeleteDialog = mutableStateOf(false)
    val showMoveDialog = mutableStateOf(false)
    val focusedCarFeed = mutableStateOf<ArchiveFeedUiModel?>(null)

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.intValue = index
    }

    fun onCarDetail(madeCar: ArchiveFeedUiModel) {
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

    fun deleteMadeCarFeed() {
        viewModelScope.launch {
            val isSuccess = deleteMadeCarFeedUseCase(focusedCarFeed.value!!.id)
            if (isSuccess) {
                refreshMadeCarFeed.value = false
                refreshMadeCarFeed.value = true
            }
        }
    }

    fun deleteSavedCarFeed(feedId: Int) {

    }

}