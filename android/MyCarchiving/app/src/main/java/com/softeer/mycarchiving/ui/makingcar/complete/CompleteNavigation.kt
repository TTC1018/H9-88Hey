package com.softeer.mycarchiving.ui.makingcar.complete

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToComplete(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_COMPLETE, navOptions)
}

fun NavGraphBuilder.completeScreen() {
    composable(route = MakingCarDestinations.SELECT_COMPLETE) {

    }
}