package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.viniciuscoscia.catchacat.presenter.ui.screen.breeddetails.BreedDetailsScreen

fun NavGraphBuilder.breedDetailsScreenRoute(navigator: Navigator) {
    composable(
        route = Screen.BreedDetails.ROUTE,
        arguments = listOf(Screen.BreedDetails.NavArgs.breedId)
    ) { entry ->
//        val viewModel = getViewModel<ImageGalleriesViewModel>()
//        val scaffoldState = rememberScaffoldState()
//        val coroutineScope = rememberCoroutineScope()
//
//        when (val event = viewModel.uiEvents.collectAsState(initial = null).value) {
//            is UIEvents.Navigate -> {
//                navigator.navigateTo(event.route)
//            }
//            is UIEvents.ShowSnackbar -> {
//                coroutineScope.launch {
//                    scaffoldState.snackbarHostState.showSnackbar(event.message)
//                }
//            }

//            else -> {
//                Timber.d("Received event $event")
//            }
//        }

        entry.arguments?.let { bundle ->
            val breedId = bundle.getString(Screen.BreedDetails.NavArgs.breedId.name, "0")
            BreedDetailsScreen(breedId = breedId)
        }
    }
}