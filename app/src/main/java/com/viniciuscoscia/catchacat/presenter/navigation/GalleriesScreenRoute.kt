package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.viniciuscoscia.catchacat.presenter.ui.model.UIEvents
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.ImageGalleriesScreen
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.ImageGalleriesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

fun NavGraphBuilder.galleriesScreenRoute(navigator: Navigator) {
    composable(route = Screen.MainScreen.route) {
        val viewModel = getViewModel<ImageGalleriesViewModel>()
        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()

        when (val event = viewModel.uiEvents.collectAsState(initial = null).value) {
            is UIEvents.Navigate -> {
                navigator.navigateTo(event.route)
            }
            is UIEvents.ShowSnackbar -> {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
            }
            else -> {
                Timber.d("Received event $event")
            }
        }

        ImageGalleriesScreen(
            imageGalleries = viewModel.imageGalleries,
            onTitleClicked = {
                viewModel.onGalleryTitleClicked(it)
            },
            scaffoldState = scaffoldState
        )
    }
}