package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.softeer.mycarchiving.navigation.MakingCarDestinations
import com.softeer.mycarchiving.ui.HyundaiAppState

fun NavController.navigateToSelectModel(navOptions: NavOptions? = null) {
    navigate(MakingCarDestinations.SELECT_MODEL.route, navOptions)
}

fun NavGraphBuilder.selectModelScreen(viewModelStoreOwner: ViewModelStoreOwner) {
    composable(route = MakingCarDestinations.SELECT_MODEL.route) {
        SelectModelRoute(viewModelOwner = viewModelStoreOwner)
    }
}