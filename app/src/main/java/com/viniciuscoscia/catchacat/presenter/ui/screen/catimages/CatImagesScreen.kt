package com.viniciuscoscia.catchacat.presenter.ui.screen.catimages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel

@Composable
fun CatImagesScreen(
    navController: NavHostController,
    viewModel: CatImagesViewModel = getViewModel()
) {
    Scaffold(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {

        }
    }
}