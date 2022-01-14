package com.thackbarth.playwithjetpack.navigation.screens.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.model.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun ShoppingCart(navController: NavController, mainViewModel: MainViewModel) {


    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Shopping Cart") },

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
            Text(text = "Shopping cart goes here")
        })

}
