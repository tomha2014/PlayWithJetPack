package com.thackbarth.playwithjetpack.navigation.screens.shoppingCart

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.repos.ProductDatabaseRepo
import com.thackbarth.playwithjetpack.repos.ShoppingCartRepo
import com.thackbarth.playwithjetpack.repos.StoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel
@Inject
constructor(
    private val repository: ProductDatabaseRepo,
    private val cartRepo: ShoppingCartRepo,
    private val storeRepo: StoreRepo
) : ViewModel() {

    var cartSize: Int by mutableStateOf(0)
    val productList = MutableStateFlow<List<Product>>(emptyList())

    init {
        Log.d(Constants.TAG, "init")
        updateLists()
    }

    fun updateLists() {
        viewModelScope.launch(Dispatchers.IO) {
            cartSize = cartRepo.getCount()
            Log.d("debug", "Cart Size: $cartSize")

            val products = storeRepo.getListOfProducts().data
            if (products!= null) {
                productList.value = products
                
            }
        }
    }

    fun buildSharpingCartItems(){

    }



}