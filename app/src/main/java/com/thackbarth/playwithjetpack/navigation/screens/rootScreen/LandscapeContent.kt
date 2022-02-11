package com.thackbarth.playwithjetpack

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.details.DetailsScreen
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.AppViewModel
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreen
import com.thackbarth.playwithjetpack.navigation.screens.rootScreen.AppBar
import com.thackbarth.playwithjetpack.navigation.screens.shoppingCart.ShoppingCart
import com.thackbarth.playwithjetpack.navigation.screens.splash.SplashScreen
import com.thackbarth.playwithjetpack.ui.theme.PlayWithJetPackTheme
import kotlinx.coroutines.InternalCoroutinesApi


@OptIn(ExperimentalAnimationApi::class)
@InternalCoroutinesApi
@Composable
fun LandscapeContent(viewModel: AppViewModel) {
    val backButtonVisibleState = rememberSaveable { (mutableStateOf(true)) }
    val appBarVisibleState = rememberSaveable { (mutableStateOf(true)) }
    val cartButtonVisibleState = rememberSaveable { (mutableStateOf(false)) }

    val showDrawer = false

    PlayWithJetPackTheme {
        val navController = rememberNavController()

        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    AppBar(
                        navController = navController,
                        viewModel = viewModel,
                        title = "What Not Store Front",
                        backButtonVisibleState = backButtonVisibleState,
                        appBarVisibleState = appBarVisibleState,
                        cartButtonVisibleState = cartButtonVisibleState
                    )
                },
                bottomBar = {},

                ) {

                Row() {
                    Column(Modifier.width(100.dp)) {
                        Text(text = "Catagories")
                    }
                    Column() {
                        NavHost(
                            navController = navController,
                            startDestination = ApplicationScreens.SplashScreen.name
                        ) {

                            composable(ApplicationScreens.SplashScreen.name) {
                                LaunchedEffect(Unit) {
                                    backButtonVisibleState.value = false
                                    appBarVisibleState.value = false
                                    cartButtonVisibleState.value = false
                                }
                                val homeViewModel = hiltViewModel<AppViewModel>()
                                SplashScreen(navController, homeViewModel)
                            }

                            composable(ApplicationScreens.HomeScreen.name) {

                                LaunchedEffect(Unit) {
                                    backButtonVisibleState.value = false
                                    appBarVisibleState.value = true
                                    cartButtonVisibleState.value = true
                                }
                                val homeViewModel = hiltViewModel<AppViewModel>()
                                HomeScreen(navController, homeViewModel)
                            }

                            composable(ApplicationScreens.ShoppingCart.name) {
                                LaunchedEffect(Unit) {
                                    backButtonVisibleState.value = true
                                    appBarVisibleState.value = true
                                    cartButtonVisibleState.value = false
                                }
                                val homeViewModel = hiltViewModel<AppViewModel>()
                                ShoppingCart(navController, homeViewModel)
                            }

                            // This is a test

                            composable(
                                ApplicationScreens.DetailsScreen.name + "/{photoId}",
                                arguments = listOf(navArgument(name = "photoId") {
                                    type = NavType.IntType
                                })
                            ) { backStackEntry ->

                                LaunchedEffect(Unit) {
                                    backButtonVisibleState.value = true
                                    appBarVisibleState.value = true
                                    cartButtonVisibleState.value = false
                                }

                                DetailsScreen(
                                    navController,
                                    backStackEntry.arguments?.getInt("photoId"),
                                    viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}