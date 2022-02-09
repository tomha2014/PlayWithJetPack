package com.thackbarth.playwithjetpack.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.details.DetailsScreen
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreen
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.AppViewModel
import com.thackbarth.playwithjetpack.navigation.screens.shoppingCart.ShoppingCart
import com.thackbarth.playwithjetpack.navigation.screens.splash.SplashScreen
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalAnimationApi::class)
@InternalCoroutinesApi
@Composable
fun ApplicationNavigation(
    viewModel: AppViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = ApplicationScreens.SplashScreen.name){

        composable(ApplicationScreens.SplashScreen.name){
            val homeViewModel = hiltViewModel<AppViewModel>()
            SplashScreen( navController, homeViewModel)
        }

        composable(ApplicationScreens.HomeScreen.name){
            val homeViewModel = hiltViewModel<AppViewModel>()
            HomeScreen( navController, homeViewModel)
        }

        composable(ApplicationScreens.ShoppingCart.name){
            val homeViewModel = hiltViewModel<AppViewModel>()
            ShoppingCart( navController, homeViewModel)
        }

        // This is a test

        composable(ApplicationScreens.DetailsScreen.name+"/{photoId}",
        arguments = listOf(navArgument(name = "photoId"){type= NavType.IntType })){
            backStackEntry ->
            DetailsScreen( navController, backStackEntry.arguments?.getInt("photoId"), viewModel)
        }
    }
}
