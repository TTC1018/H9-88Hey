package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToSelectModel(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_MODEL, navOptions)
}

fun NavGraphBuilder.selectModelScreen() {
    composable(route = MakingCarDestinations.SELECT_MODEL) {

    }
}