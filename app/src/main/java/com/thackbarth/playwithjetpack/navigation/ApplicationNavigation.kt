package com.thackbarth.playwithjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.details.DetailsScreen
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreen
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.HomeScreenViewModel
import com.thackbarth.playwithjetpack.navigation.screens.shoppingCart.ShoppingCart
import com.thackbarth.playwithjetpack.navigation.screens.shoppingCart.ShoppingCartViewModel
import com.thackbarth.playwithjetpack.navigation.screens.splash.SplashScreen
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun ApplicationNavigation(
    viewModel: HomeScreenViewModel,
    shoppingCartViewModel: ShoppingCartViewModel
){

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = ApplicationScreens.HomeScreen.name){

        composable(ApplicationScreens.SplashScreen.name){
            SplashScreen( navController)
        }


        composable(ApplicationScreens.HomeScreen.name){
            HomeScreen( navController, viewModel)
        }

        composable(ApplicationScreens.ShoppingCart.name){
//            val shoppingCartViewModel = hiltViewModel<ShoppingCartViewModel>()

//            val shoppingCartViewModel: ShoppingCartViewModel by viewModels()

            ShoppingCart( navController, shoppingCartViewModel = shoppingCartViewModel)
        }

        composable(ApplicationScreens.DetailsScreen.name+"/{photoId}",
        arguments = listOf(navArgument(name = "photoId"){type= NavType.IntType })){
            backStackEntry ->
            DetailsScreen( navController, backStackEntry.arguments?.getInt("photoId"), viewModel)
        }
    }
}
