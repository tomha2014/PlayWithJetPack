package com.thackbarth.playwithjetpack.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.network.StoreApi
import com.thackbarth.playwithjetpack.repos.DatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: DatabaseRepo
) : ViewModel() {

    private val TAG = "MainViewModel"
    val productList = MutableStateFlow<List<Product>>(emptyList())
    var errorMessage: String by mutableStateOf("")


    init {
        Log.d(TAG, "init")
    }

    fun loadAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllProducts1().distinctUntilChanged().collect {
                    stuff ->
                if (stuff.isNullOrEmpty()){
                    Log.d(TAG, "out of stuff")

                    if (productList.value.isEmpty()){
                        Log.d(TAG, "database was empty of stuff, go get new stuff from server")
                        getProductListFromServer()
                    }
                } else {
                    productList.value = stuff
                }
            }
        }
    }

    private fun getProductListFromServer() {
        viewModelScope.launch {
            val apiService = StoreApi.getInstance()
            try {
                val products = apiService.get()
                Log.d(TAG, "data Loaded")
                productList.value = products
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getAllProducts(): List<Product>{
        return productList.value
    }

    fun findProductByID(id: Int): Product? {
        return productList.value.first { it.id == id }
    }

}