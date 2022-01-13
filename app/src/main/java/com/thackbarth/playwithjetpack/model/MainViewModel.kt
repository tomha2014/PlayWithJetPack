package com.thackbarth.playwithjetpack.model

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.network.StoreApi
import com.thackbarth.playwithjetpack.repos.DatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


data class Photo (
    var title: String
)

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: DatabaseRepo,
    private val storeApi: StoreApi
) : ViewModel() {


    var filterCategory: String by mutableStateOf("EveryThing")

    val productList = MutableStateFlow<List<Product>>(emptyList())
    var errorMessage: String by mutableStateOf("")
    val categoryList = MutableLiveData<List<String>>(emptyList())

    var photos: MutableLiveData<ArrayList<Photo>> = MutableLiveData()


    init {
        Log.d(Constants.TAG, "init")
    }

    fun loadAllData(){

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllProducts1().distinctUntilChanged().collect {
                    stuff ->
                if (stuff.isNullOrEmpty()){
                    Log.d(TAG, "out of stuff")
                    if (productList.value.isEmpty()){
                        Log.d(Constants.TAG, "database was empty of stuff, go get new stuff from server")
                        try {
                            val products = storeApi.get()
                            Log.d(Constants.TAG, "data Loaded")


                            // Save each item in the database for next time.
                            products.forEach{
                                repository.addProduct(it)
                            }

                            productList.value = products
                            buildCategoryList()
                        } catch (e: Exception) {
                            errorMessage = e.message.toString()
                        }
                    }
                } else {
                    productList.value = stuff
                    buildCategoryList()
                }
            }
        }
    }

    private fun buildCategoryList() {
        val lst = productList.value
        val cats = ArrayList<String>()
        cats.add("Everything")
        lst.forEach{
            if (!cats.contains(it.category)){
                cats.add(it.category)
            }
        }
         categoryList.postValue(cats)
    }

    fun getAllProducts(): List<Product>{
        return productList.value
    }

    fun findProductByID(id: Int): Product? {

        productList.value.map { Log.d(Constants.TAG, it.title) }

        return productList.value.first { it.id == id }
    }

}