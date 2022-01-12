package com.thackbarth.playwithjetpack.navigation.screens.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.composables.ProductRow
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel){

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Row Details") },
            navigationIcon = if (navController.previousBackStackEntry != null) {
                {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            } else {
                null
            }
        )
    },
        content = {
            HomeScreenContent(navController = navController, viewModel )
        })
}

@InternalCoroutinesApi
@Composable
fun HomeScreenContent(navController: NavController, vm: MainViewModel ) {

//    val viewModel: MainViewModel = viewModel()

    Log.d("MainViewModel", "here")
    vm.photos.value?.forEach {
        Log.d("MainViewModel", it.title)
    }

    Surface(color = Color.White) {
        LazyColumn {
            itemsIndexed(items = vm.productList.value) { index, item ->
                ProductRow(item) {
                    navController.navigate(route = ApplicationScreens.DetailsScreen.name + "/" + item.id)
                    Log.d("test", "you clicked on ${it.title}")
                }
            }
        }
    }
}