package com.viniciuscoscia.catchacat.presenter.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import timber.log.Timber

class Navigator {
    private val _navigationChannel = Channel<String>()
    val navigationChannel = _navigationChannel.receiveAsFlow()

    fun navigateTo(screen: Screen) {
        Timber.d("Navigating to $screen")
        _navigationChannel.trySend(screen.buildRoute())
    }
}