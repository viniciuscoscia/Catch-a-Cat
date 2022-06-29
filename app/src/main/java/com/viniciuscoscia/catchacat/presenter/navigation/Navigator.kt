package com.viniciuscoscia.catchacat.presenter.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

class Navigator {
    private val _navigationChannel = Channel<Screen>()
    val navigationChannel = _navigationChannel.receiveAsFlow()

    fun navigateTo(screen: Screen) {
        Timber.d("Navigating to $screen")
        _navigationChannel.trySend(screen)
    }
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    class CatBreedDetails(id: String) : Screen("cat_breed_details_screen")
}