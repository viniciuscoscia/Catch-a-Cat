package com.viniciuscoscia.catchacat.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.viniciuscoscia.catchacat.presenter.navigation.NavigationComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*
            * Must leave this Scaffold due Android 12 related bug
            *
            * https://issuetracker.google.com/issues/227926002?pli=1
            */
            val navController = rememberNavController()
            NavigationComponent(navController)
        }
    }
}