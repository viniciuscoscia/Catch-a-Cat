package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.viniciuscoscia.catchacat.presenter.ui.model.UIEvents
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.ImageGalleriesScreen
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.ImageGalleriesViewModel
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

fun NavGraphBuilder.galleriesScreenRoute(navigator: Navigator) {
    composable(route = Screen.ImageGallery.BASE_ROUTE) {
        val viewModel = getViewModel<ImageGalleriesViewModel>()
        val scaffoldState = rememberScaffoldState()
//        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(true) {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    is UIEvents.Navigate -> {
                        navigator.navigateTo(event.route)
                    }
                    else -> {
                        Timber.d("Received event $event")
                    }
                }
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