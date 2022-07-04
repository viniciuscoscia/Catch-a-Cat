package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.viniciuscoscia.catchacat.presenter.navigation.ScreenArgs.BREED_ID

private const val IMAGE_GALLERY_BASE_ROUTE = "image_gallery"
private const val BREED_DETAILS_BASE_ROUTE = "breed_details_screen"

sealed class Screen(val route: String) {
    object ImageGallery : Screen(IMAGE_GALLERY_BASE_ROUTE)
    class BreedDetails(breedId: String) : Screen("$BREED_DETAILS_BASE_ROUTE/$breedId") {
        companion object : Screen("$BREED_DETAILS_BASE_ROUTE/{$BREED_ID}") {
            val breedIdNavArgument = navArgument(BREED_ID) { type = NavType.StringType }
        }
    }
}

object ScreenArgs {
    const val BREED_ID = "breedId"
}