package com.softeer.mycarchiving.ui.makingcar.selectcolor

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToSelectColor(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_COLOR.route, navOptions)
}

fun NavGraphBuilder.selectColorScreen() {
    composable(route = MakingCarDestinations.SELECT_COLOR.route) {
        SelectColorRoute()
    }
}