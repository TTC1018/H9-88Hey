package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softeer.domain.usecase.archiving.GetCarDetailsUseCase
import com.softeer.domain.usecase.myarchive.AddBookmarkUseCase
import com.softeer.domain.usecase.myarchive.CheckBookmarkedUseCase
import com.softeer.domain.usecase.myarchive.DeleteBookmarkUseCase
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.ui.archiving.KEY_ARCHIVE_DETAIL
import com.softeer.mycarchiving.util.mutableStateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArchiveDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCarDetailsUseCase: GetCarDetailsUseCase,
    private val addBookmarkUseCase: AddBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
    checkBookmarkedUseCase: CheckBookmarkedUseCase,
) : ViewModel() {

    val feedId = savedStateHandle.getStateFlow(KEY_ARCHIVE_DETAIL, "")

    val details = feedId.flatMapLatest { id ->
        if (id.isNotEmpty()) {
            getCarDetailsUseCase(id).map { it?.asUiModel() }
        } else {
            emptyFlow()
        }
    }

    private val _isSaved = feedId.map {
        checkBookmarkedUseCase(it)
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