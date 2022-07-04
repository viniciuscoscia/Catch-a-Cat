package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val IMAGE_GALLERY_BASE_ROUTE = "image_gallery"
private const val BREED_DETAILS_BASE_ROUTE = "breed_details_screen"

sealed class Screen(val route: String) {
    object ImageGallery : Screen(IMAGE_GALLERY_BASE_ROUTE) {
        const val BASE_ROUTE = IMAGE_GALLERY_BASE_ROUTE
    }

    class BreedDetails(breedId: String) : Screen("$BREED_DETAILS_BASE_ROUTE/$breedId") {
        object NavArgs {
            val breedId = navArgument(BREED_ID) { type = NavType.StringType }
        }

        companion object {
            const val BREED_ID = "breedId"
            const val BASE_ROUTE = "$BREED_DETAILS_BASE_ROUTE/{$BREED_ID}"
        }
    }
}