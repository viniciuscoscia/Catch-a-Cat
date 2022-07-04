package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.viniciuscoscia.catchacat.presenter.ui.screen.breeddetails.BreedDetailsScreen

fun NavGraphBuilder.breedDetailsScreenRoute() {
    composable(
        route = Screen.BreedDetails.BASE_ROUTE,
        arguments = listOf(Screen.BreedDetails.NavArgs.breedId)
    ) { entry ->
        entry.arguments?.let { bundle ->
            val breedId = bundle.getString(Screen.BreedDetails.NavArgs.breedId.name, "0")
            BreedDetailsScreen(breedId = breedId)
        }
    }
}