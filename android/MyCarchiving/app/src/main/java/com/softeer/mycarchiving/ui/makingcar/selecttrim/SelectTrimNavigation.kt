package com.softeer.mycarchiving.ui.makingcar.selecttrim

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToSelectTrim(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_TRIM, navOptions)
}

fun NavGraphBuilder.selectTrimScreen() {
    composable(route = MakingCarDestinations.SELECT_TRIM) {

    }
}