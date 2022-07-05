@file:OptIn(ExperimentalAnimationApi::class)

package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.viniciuscoscia.catchacat.presenter.ui.screen.breeddetails.BreedDetailsScreen

fun NavGraphBuilder.breedDetailsScreenRoute() {
    composable(
        route = Screen.BreedDetails.BASE_ROUTE,
        arguments = listOf(Screen.BreedDetails.NavArgs.breedId),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
    ) { entry ->
        entry.arguments?.let { bundle ->
            val breedId = bundle.getString(Screen.BreedDetails.NavArgs.breedId.name, "0")
            BreedDetailsScreen(breedId = breedId)
        }
    }
}