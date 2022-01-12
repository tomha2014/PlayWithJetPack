package com.thackbarth.playwithjetpack.model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.network.StoreApi
import com.thackbarth.playwithjetpack.repos.DatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.log


data class Photo (
    var title: String
)

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: DatabaseRepo
) : ViewModel() {

    private val TAG = "MainViewModel"

    val productList = MutableStateFlow<List<Product>>(emptyList())
    var errorMessage: String by mutableStateOf("")

    var photos: MutableLiveData<ArrayList<Photo>> = MutableLiveData()


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
                        try {
                            val apiService = StoreApi.getInstance()
                            val products = apiService.get()
                            Log.d(TAG, "data Loaded")


                            // Save each item in the database for next time.
                            products.forEach{
                                repository.addProduct(it)
                            }

                            productList.value = products

                        } catch (e: Exception) {
                            errorMessage = e.message.toString()
                        }
                    }
                } else {
                    productList.value = stuff
                }
            }
        }
    }

    fun getAllProducts(): List<Product>{
        return productList.value
    }

    fun findProductByID(id: Int): Product? {

        productList.value.map { Log.d(TAG, it.title) }

        return productList.value.first { it.id == id }
    }

}