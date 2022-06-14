package com.viniciuscoscia.catchacat.presenter.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

object TextFields {
    @Composable
    fun Title(
        text: String,
        modifier: Modifier = Modifier
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = modifier
        )
    }
}