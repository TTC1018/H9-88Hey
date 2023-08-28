package com.softeer.mycarchiving.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

@Composable
fun MyCarchivingTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(primaryContainer = HyundaiSand)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primaryContainer.toArgb()
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
