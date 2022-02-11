package com.thackbarth.playwithjetpack.navigation.screens.rootScreen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.AppViewModel

@Composable
fun AppBar(
    navController: NavController, title: String = "Title", viewModel: AppViewModel,
    backButtonVisibleState: MutableState<Boolean>,
    appBarVisibleState: MutableState<Boolean>,
    cartButtonVisibleState: MutableState<Boolean>
) {
    if (appBarVisibleState.value) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = if (backButtonVisibleState.value) {
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
            },
            actions = {
                if (cartButtonVisibleState.value) {
                    if (viewModel.cartList.collectAsState().value.isNotEmpty()) {
                        IconButton(onClick = {
                            navController.navigate(route = ApplicationScreens.ShoppingCart.name)
                        }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "ShoppingCart"
                            )
                        }
                    }
                }

            }
        )
    }

}
