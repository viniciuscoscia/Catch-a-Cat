package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(navController: NavHostController, navigator: Navigator) {
    LaunchedEffect(true) {
        navigator.navigationChannel.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = Screen.ImageGallery.route
    ) {
        galleriesScreenRoute(navigator)
        breedDetailsScreenRoute()
    }
}