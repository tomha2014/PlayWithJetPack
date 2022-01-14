package com.thackbarth.playwithjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.ui.DetailsScreen
import com.thackbarth.playwithjetpack.navigation.screens.ui.HomeScreen
import com.thackbarth.playwithjetpack.navigation.screens.ui.ShoppingCart
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun ApplicationNavigation(viewModel: MainViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = ApplicationScreens.HomeScreen.name){

        composable(ApplicationScreens.HomeScreen.name){
            HomeScreen( navController, viewModel)
        }

        composable(ApplicationScreens.ShoppingCart.name){
            ShoppingCart( navController, viewModel)
        }

        composable(ApplicationScreens.DetailsScreen.name+"/{photoId}",
        arguments = listOf(navArgument(name = "photoId"){type= NavType.IntType })){
            backStackEntry ->
            DetailsScreen( navController, backStackEntry.arguments?.getInt("photoId"), viewModel)
        }
    }
}
