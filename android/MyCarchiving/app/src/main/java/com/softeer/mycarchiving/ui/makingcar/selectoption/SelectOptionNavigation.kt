package com.softeer.mycarchiving.ui.makingcar.selectoption

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations

fun NavController.navigateToSelectOption(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_OPTION, navOptions)
}

fun NavGraphBuilder.selectOptionScreen() {
    composable(route = MakingCarDestinations.SELECT_OPTION) {

    }
}