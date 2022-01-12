package com.thackbarth.playwithjetpack.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.network.StoreApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
object MainViewModel : ViewModel() {

    var productList:List<Product> by mutableStateOf(emptyList())

    var errorMessage: String by mutableStateOf("")


    fun getMovieList() {
        viewModelScope.launch {
            val apiService = StoreApi.getInstance()
            try {
                val products = apiService.get()
                productList = products
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}