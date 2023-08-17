package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
    composable(
        route = MakingCarDestinations.SELECT_MODEL.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it } ) }
    ) {
        SelectModelRoute(viewModelOwner = viewModelStoreOwner)
    }
}