package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(navController: NavHostController, navigator: Navigator) {
    LaunchedEffect(true) {
        navigator.navigationChannel.onEach {
            navController.navigate(it.route)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        galleriesScreenRoute(navigator)
        breedDetailsScreenRoute(navigator)
    }
}

fun NavGraphBuilder.breedDetailsScreenRoute(navigator: Navigator) {
//        composable(
//            route = Screen.TVShowDetailsScreen.route + "/{$showIdArg}",
//            arguments = listOf(
//                navArgument(showIdArg) {
//                    type = NavType.IntType
//                    defaultValue = 0
//                    nullable = false
//                }
//            )
//        ) { entry ->
//            TVShowDetailsScreen(navController, entry.arguments!!.getInt(showIdArg, 0))
//        }
}
