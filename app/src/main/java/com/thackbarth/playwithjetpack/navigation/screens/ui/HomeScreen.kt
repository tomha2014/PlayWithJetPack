package com.thackbarth.playwithjetpack.navigation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.widgets.Greeting


@Composable
fun HomeScreen(navController: NavController, vm: MainViewModel){
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn {
            itemsIndexed(items = vm.photoListResponse) { index, item ->
                Greeting(item.title)
            }
        }

    }
}