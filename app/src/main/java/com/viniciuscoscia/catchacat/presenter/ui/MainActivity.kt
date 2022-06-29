package com.viniciuscoscia.catchacat.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.viniciuscoscia.catchacat.presenter.navigation.NavigationComponent
import com.viniciuscoscia.catchacat.presenter.navigation.Navigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navigator = remember { Navigator() }
            NavigationComponent(navController, navigator)
        }
    }
}