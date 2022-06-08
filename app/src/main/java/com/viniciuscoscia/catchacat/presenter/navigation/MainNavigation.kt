package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.viniciuscoscia.catchacat.presenter.ui.screen.catimages.CatGalleriesScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            CatGalleriesScreen(navController = navController)
        }

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
}

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object CatBreedDetails : Screen("cat_breed_details_screen")
}