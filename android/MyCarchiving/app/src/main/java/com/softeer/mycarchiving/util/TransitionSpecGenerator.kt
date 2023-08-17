package com.softeer.mycarchiving.util

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith


fun fadeInAndOut(
    fadeInDuration: Int = 1000,
    fadeOutDuration: Int = 1000,
) = fadeIn(
    tween(
        durationMillis = fadeInDuration,
        easing = LinearOutSlowInEasing
    )
).togetherWith(
    fadeOut(
        tween(
            durationMillis = fadeOutDuration,
            easing = FastOutSlowInEasing
        )
    )
)