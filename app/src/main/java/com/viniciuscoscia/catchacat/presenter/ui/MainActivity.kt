@file:OptIn(ExperimentalAnimationApi::class)

package com.viniciuscoscia.catchacat.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.viniciuscoscia.catchacat.presenter.navigation.NavigationComponent
import com.viniciuscoscia.catchacat.presenter.navigation.Navigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            val navigator = remember { Navigator() }
            Scaffold {
                NavigationComponent(navController, navigator)
            }
        }
    }
}

@Composable
private fun ExperimentalAnimationNav() {
    val navController = rememberAnimatedNavController()
    val blueScreen = "Blue"
    val redScreen = "Red"
    AnimatedNavHost(navController, startDestination = blueScreen) {
        composable(
            blueScreen,
            enterTransition = {
                when (initialState.destination.route) {
                    redScreen ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    redScreen ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    redScreen ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    redScreen ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            }
        ) { BlueScreen(navController) }
        composable(
            redScreen,
            enterTransition = {
                when (initialState.destination.route) {
                    blueScreen ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    blueScreen ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    blueScreen ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    blueScreen ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            }
        ) { RedScreen(navController) }
    }
}

@Composable
fun RedScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)
        .clickable { navController.navigate("Blue") }) {

    }
}

@Composable
fun BlueScreen(navController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)
        .clickable { navController.navigate("Red") }) {

    }
}
