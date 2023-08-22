package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.softeer.domain.usecase.archiving.GetCarDetailsUseCase
import com.softeer.mycarchiving.mapper.asDetailUiModel
import com.softeer.mycarchiving.mapper.asUiModel
import com.softeer.mycarchiving.ui.archiving.KEY_ARCHIVE_DETAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArchiveDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCarDetailsUseCase: GetCarDetailsUseCase,
) : ViewModel() {

    val feedId = savedStateHandle.getStateFlow(KEY_ARCHIVE_DETAIL, -1L)

    val details = feedId.flatMapLatest { id ->
        if (id > 0) {
            getCarDetailsUseCase(id).map { it?.asUiModel() }
        } else {
            emptyFlow()
        }
    }

}