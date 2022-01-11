package com.thackbarth.playwithjetpack.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.model.Photo
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun DetailsScreen(navController: NavController, id: Int?){

    var photo = MainViewModel.photoListResponse.filter { it.id == id  }.first()

    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Row(modifier = Modifier.padding(20.dp)){
            Image(
                painter = rememberImagePainter(photo.url),
                contentDescription = "${photo.title}"
            )
        }

        Text(text = "Details go here: ${photo.title}")
    }




}