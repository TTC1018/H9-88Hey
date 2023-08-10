package com.softeer.mycarchiving.ui.makingcar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.softeer.mycarchiving.navigation.MainDestination
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.ui.makingcar.complete.completeScreen
import com.softeer.mycarchiving.ui.makingcar.selectcolor.selectColorScreen
import com.softeer.mycarchiving.ui.makingcar.selectmodel.selectModelScreen
import com.softeer.mycarchiving.ui.makingcar.selectoption.selectOptionScreen
import com.softeer.mycarchiving.ui.makingcar.selecttrim.selectTrimScreen

fun NavController.navigateToMakingCar(navOptions: NavOptions? = null) {
    navigate(
        MainDestination.MAKING_CAR.route,
        navOptions
    )
}

fun NavGraphBuilder.makingCarGraph() {
    navigation(startDestination = MakingCarDestinations.SELECT_MODEL.route, route = MainDestination.MAKING_CAR.route) {
        selectModelScreen()
        selectTrimScreen()
        selectColorScreen()
        selectOptionScreen()
        completeScreen()
    }
}