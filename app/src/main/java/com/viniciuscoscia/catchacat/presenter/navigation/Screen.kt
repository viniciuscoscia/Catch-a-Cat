package com.viniciuscoscia.catchacat.presenter.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val IMAGE_GALLERY_BASE_ROUTE = "image_gallery"
private const val BREED_DETAILS_BASE_ROUTE = "breed_details_screen"

sealed interface Screen {
    object ImageGallery : Screen {
        const val ROUTE = IMAGE_GALLERY_BASE_ROUTE
        override fun buildRoute(): String = ROUTE
    }

    class BreedDetails(private val breedId: String) : Screen {
        override fun buildRoute(): String = "$BREED_DETAILS_BASE_ROUTE/$breedId"

        object NavArgs {
            val breedId = navArgument(BREED_ID) { type = NavType.StringType }
        }

        companion object {
            const val BREED_ID = "breedId"
            const val ROUTE = "$BREED_DETAILS_BASE_ROUTE/{$BREED_ID}"
        }
    }

    fun buildRoute(): String
}