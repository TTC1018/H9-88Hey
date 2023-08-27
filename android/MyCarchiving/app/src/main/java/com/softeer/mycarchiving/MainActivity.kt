package com.softeer.mycarchiving

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.softeer.mycarchiving.ui.HyundaiApp
import com.softeer.mycarchiving.ui.theme.MyCarchivingTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCarchivingTheme {
                HyundaiApp()
            }
        }
    }
}