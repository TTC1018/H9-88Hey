package com.softeer.mycarchiving.ui.makingcar

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.util.MakeCarProcess.progressItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MakingCarViewModel @Inject constructor(): ViewModel() {

    private val _selectedCarModel = MutableStateFlow("펠리세이드")
    val selectedCarModel: StateFlow<String> = _selectedCarModel

    private val _totalPrice = MutableStateFlow(0)
    val totalPrice: StateFlow<Int> = _totalPrice

    private val _showSummary = MutableStateFlow(false)
    val showSummary: StateFlow<Boolean> = _showSummary

    val currentProgress = MutableStateFlow(progressItems[0])
    val currentProgressChildId = MutableStateFlow(if (progressItems[0].needNoChildProgress) -1 else 0)
    val currentProgressChildren = MutableStateFlow(progressItems[0].children)
    val remainProgressCountList = MutableStateFlow(mutableListOf(progressItems.size - 1, progressItems.size))
    val progressEnd = MutableStateFlow(false)

    fun openSummary() {
        _showSummary.value = true
    }

    fun closeSummary() {
        _showSummary.value = false
    }

    fun onNextProgress(navigate: () -> Unit) {
        if (progressEnd.value) {
            return
        }
        // 다음 세부 단계가 남았을 경우
        if (currentProgressChildId.value < currentProgress.value.children.size - 1) {
            if (currentProgressChildId.value < 0) navigate()
            currentProgressChildId.value = currentProgressChildId.value + 1
            return
        }
        // 다음 단계가 남았을 경우
        val currentId = currentProgress.value.id
        if (currentId < progressItems.size - 1) {
                progressItems[currentId + 1].let {
                currentProgress.value = it
                currentProgressChildren.value = it.children
                currentProgressChildId.value = it.children.first().id
            }
            remainProgressCountList.value = remainProgressCountList.value.also {
                it.removeFirst()
            }
        } else {
            // 모든 단계가 끝나는 경우
            progressEnd.value = true
        }
        navigate()
    }

    fun onBackProgress(navigate: () -> Unit) {
        if (progressEnd.value) {
            progressEnd.value = false
            navigate()
            return
        }
        // 이전 세부 단계가 남았을 경우
        val minChildId = if (currentProgress.value.needNoChildProgress) -1 else 0
        if (currentProgressChildId.value > minChildId) {
            currentProgressChildId.value = currentProgressChildId.value - 1
            if (currentProgressChildId.value < 0) navigate()
            return
        }
        // 이전 단계가 남았을 경우
        val currentId = currentProgress.value.id
        if (currentId > progressItems[0].id) {
                progressItems[currentId - 1].let {
                currentProgress.value = it
                currentProgressChildren.value = it.children
                currentProgressChildId.value = it.children.last().id
            }
            remainProgressCountList.value = remainProgressCountList.value.also {
                val newProgressCount = if (it.isEmpty()) progressItems.size else it.last() - 1
                it.add(0, newProgressCount)
            }
            navigate()
            return
        }
    }
}