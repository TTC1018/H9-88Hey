package com.softeer.mycarchiving.ui.makingcar

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.util.MakeCarProcess.makeCarProcess
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

    val currentProgressId = MutableStateFlow(0)
    val currentProgressChildId = MutableStateFlow(-1)
    val progressEnd = MutableStateFlow(false)

    fun openSummary() {
        _showSummary.value = true
    }

    fun closeSummary() {
        _showSummary.value = false
    }

    fun onNextProgress(navigate: () -> Unit) {
        // 완성 화면일 경우
        if (progressEnd.value) {
            return
        }

        // 다음 세부 단계가 남았을 경우
        currentProgressChildId.value.let { childId ->
            if (childId < makeCarProcess[currentProgressId.value].children.last().id) {
                if (childId < 0) navigate()
                currentProgressChildId.value = childId + 1
                return
            }
        }

        // 다음 단계가 남았을 경우
        currentProgressId.value.let { progressId ->
            if (progressId < makeCarProcess.last().id) {
                currentProgressId.value = progressId + 1
                currentProgressChildId.value = makeCarProcess[progressId + 1].children.first().id
            } else { // 모든 단계가 끝나는 경우
                progressEnd.value = true
            }
        }
        navigate()
    }

    fun onBackProgress(navigate: () -> Unit) {
        // 완성 화면일 경우
        if (progressEnd.value) {
            progressEnd.value = false
            navigate()
            return
        }

        // 이전 세부 단계가 남았을 경우
        val minChildId = if (makeCarProcess[currentProgressId.value].needNoChild) -1 else 0
        if (currentProgressChildId.value > minChildId) {
            currentProgressChildId.value = currentProgressChildId.value - 1
            return
        }

        // 이전 단계가 남았을 경우
        currentProgressId.value.let { progressId ->
            if (progressId > makeCarProcess.first().id) {
                currentProgressId.value = progressId - 1
                currentProgressChildId.value = makeCarProcess[progressId - 1].children.last().id
            } else { // 이전 단계가 없는 경우
                return
            }
        }
        navigate()
    }
}