package com.viniciuscoscia.catchacat.presenter.ui.model

import com.viniciuscoscia.catchacat.presenter.navigation.Screen

sealed interface UIEvents {
    data class ShowSnackbar(val message: String) : UIEvents
    data class Navigate(val route: Screen) : UIEvents
}