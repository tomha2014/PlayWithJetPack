package com.thackbarth.playwithjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.navigation.screens.HomeScreen

@Composable
fun ApplicationNaviagation(vm: MainViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = ApplicationScreens.HomeScreen.name){

        composable(ApplicationScreens.HomeScreen.name){
            HomeScreen( navController, vm)
        }
    }
}
