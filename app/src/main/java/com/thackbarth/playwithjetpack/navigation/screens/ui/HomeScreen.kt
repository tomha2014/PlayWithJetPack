package com.thackbarth.playwithjetpack.navigation.screens.ui

import android.util.Log
import android.widget.HorizontalScrollView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thackbarth.playwithjetpack.composables.ProductRow
import com.thackbarth.playwithjetpack.model.MainViewModel
import com.thackbarth.playwithjetpack.navigation.screens.ApplicationScreens
import com.thackbarth.playwithjetpack.widgets.ButtonBar
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect


@InternalCoroutinesApi
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel){

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Row Details") },
            navigationIcon = if (navController.previousBackStackEntry != null) {
                {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            } else {
                null
            }
        )
    },
        content = {
            HomeScreenContent(navController = navController, viewModel )
        })
}

@InternalCoroutinesApi
@Composable
fun HomeScreenContent(navController: NavController, vm: MainViewModel ) {

// THIS WORKS!
    val lst = vm.productList.collectAsState().value

    val cats = vm.categoryList.value

    Log.d("category", "==========")
    cats?.let {
        it.forEach {category ->
            Log.d("category", category)
        }
    }

    Surface(color = Color.White) {

        Column() {
            Row() {
                ScrollableTabRow(selectedTabIndex = 0, modifier = Modifier.fillMaxWidth()) {
                    for (category in vm.categoryList.value!!) {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.secondary,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            LazyColumn {

                itemsIndexed(items = lst) { index, item ->
                    ProductRow(item) {
                        navController.navigate(route = ApplicationScreens.DetailsScreen.name + "/" + item.id)
                        Log.d("test", "you clicked on ${it.title}")
                    }
                }
            }
        }
    }
}