package com.viniciuscoscia.catchacat.presenter.ui.screen.breeddetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.viniciuscoscia.catchacat.presenter.ui.theme.CatchACatTheme

@Composable
fun BreedDetailsScreen(
    breedId: String,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    CatchACatTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(text = breedId)
            }
        }
    }
}