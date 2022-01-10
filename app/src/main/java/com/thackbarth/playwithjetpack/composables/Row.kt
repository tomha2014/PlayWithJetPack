package com.thackbarth.playwithjetpack.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Row(name: String) {
    Text(text = "Hello $name!")
}
