package com.viniciuscoscia.catchacat.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.viniciuscoscia.catchacat.presenter.navigation.NavigationComponent
import com.viniciuscoscia.catchacat.presenter.ui.theme.CatchACatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatchACatTheme {
                /*
                * Must leave this Scaffold due Android 12 related bug
                *
                * https://issuetracker.google.com/issues/227926002?pli=1
                */
                Scaffold {
                    val navController = rememberNavController()
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatchACatTheme {
        Greeting("Android")
    }
}