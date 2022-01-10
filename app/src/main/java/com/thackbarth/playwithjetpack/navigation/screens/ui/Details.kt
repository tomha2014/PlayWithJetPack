package com.thackbarth.playwithjetpack.navigation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.model.Photo

@Composable
fun DetailsScreen(navController: NavController, id: Int?){

    var photo = MainViewModel.photoListResponse.filter { it.id == id  }.first()
    Text(text = "Details go here: ${photo.title}")

}