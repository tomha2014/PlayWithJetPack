package com.thackbarth.playwithjetpack.navigation.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.widgets.Greeting
import com.thackbarth.playwithjetpack.widgets.PhotoRow


@Composable
fun HomeScreen(navController: NavController){
    // A surface container using the 'background' color from the theme
    Surface(color = Color.White) {//MaterialTheme.colors.background
        LazyColumn {
            itemsIndexed(items = MainViewModel.photoListResponse) { index, item ->
                PhotoRow(item){
                    navController.navigate( route = ApplicationScreens.DetailsScreen.name)
                    Log.d("test","you clicked on ${it.title}")
                }
            }
        }

    }
}