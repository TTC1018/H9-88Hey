package com.softeer.mycarchiving.ui.myarchive.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.domain.usecase.archiving.GetCarDetailsUseCase
import com.softeer.domain.usecase.myarchive.AddBookmarkUseCase
import com.softeer.domain.usecase.myarchive.CheckBookmarkedUseCase
import com.softeer.domain.usecase.myarchive.DeleteBookmarkUseCase
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.ui.myarchive.KEY_MYARCHIVE_FEED_ID
import com.softeer.mycarchiving.util.mutableStateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private val TAG = MyArchiveDetailViewModel::class.simpleName

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MyArchiveDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCarDetailsUseCase: GetCarDetailsUseCase,
    checkBookmarkedUseCase: CheckBookmarkedUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
) : ViewModel() {

    val feedId = savedStateHandle.getStateFlow(KEY_MYARCHIVE_FEED_ID, "")

    val details = feedId.flatMapLatest { id ->
        if (id.isNotEmpty()) {
            getCarDetailsUseCase(id).map { it?.asUiModel() }
        } else {
            emptyFlow()
        }
    }

    private val _isSaved = feedId.map {
        if (it.isNotEmpty())
            checkBookmarkedUseCase(it)
        else
            false
    }.mutableStateIn(
        scope = viewModelScope,
        initialValue = false,
    )
    val isSaved: StateFlow<Boolean> = _isSaved

    fun switchBookmarkState() {
        _isSaved.value = _isSaved.value.not()
    }

    fun addBookmark() {
        viewModelScope.launch {
            addBookmarkUseCase(feedId.value)
        }
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            deleteBookmarkUseCase(feedId.value)
        }
    }

    fun saveBookmarkState() {
        when (_isSaved.value) {
            true -> addBookmark()
            false -> deleteBookmark()
        }
    }

}