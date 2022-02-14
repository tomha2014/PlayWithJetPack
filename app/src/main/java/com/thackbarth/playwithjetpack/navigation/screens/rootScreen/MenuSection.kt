package com.thackbarth.playwithjetpack.navigation.screens.rootScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thackbarth.playwithjetpack.navigation.screens.homeScreen.AppViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(ExperimentalAnimationApi::class)
@InternalCoroutinesApi
@Composable
fun MenuSection(viewModel: AppViewModel) {

    Column(Modifier.background(Color.White)) {
        Text( text = "WhatNot Store", color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        viewModel.categoryList.value?.forEach{ title ->
            Text(color = Color.Black,
                text = title)
        }

    }

}