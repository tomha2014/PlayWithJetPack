package com.thackbarth.playwithjetpack.navigation.screens

import java.lang.IllegalArgumentException


enum class ApplicationScreens {
    HomeScreen,
    ShoppingCart,
    SplashScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): ApplicationScreens = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw  IllegalArgumentException("Route $route not found")
        }
    }
}