package com.softeer.mycarchiving.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.mutableStateIn(
    scope: CoroutineScope,
    initialValue: T
): MutableStateFlow<T> {
    val mutableStateFlow = MutableStateFlow(initialValue)
    scope.launch {
        collect(mutableStateFlow)
    }
    return mutableStateFlow
}