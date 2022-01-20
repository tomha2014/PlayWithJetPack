package com.thackbarth.playwithjetpack.navigation.screens.shoppingCart

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.repos.DatabaseRepo
import com.thackbarth.playwithjetpack.repos.StoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
//
//@HiltViewModel
//class ShoppingCartViewModel
//@Inject
//constructor(
//    private val repository: DatabaseRepo,
//    private val storeRepo: StoreRepo
//) : ViewModel() {
//
//    var cartSize: Int by mutableStateOf(0)
//    val productList = MutableStateFlow<List<Product>>(emptyList())
//    val cartItemList = MutableStateFlow<List<CartItem>>(emptyList())
//
//    init {
//        Log.d(Constants.TAG, "init")
////        updateLists()
//    }
//
//    fun updateLists() {
////        viewModelScope.launch(Dispatchers.IO) {
////            cartSize = repository.countOfCartItems()
////            Log.d("debug", "Cart Size: $cartSize")
////
////            repository.getAllItems().distinctUntilChanged().collect { cartItems ->
////                if (cartItems.isNotEmpty()){
////                    cartItemList.value = cartItems
////                }
////            }
////
////            val products = storeRepo.getListOfProducts().data
////            if (products!= null) {
////                productList.value = products
////
////            }
////        }
//    }
//
//
//    fun getProductForProductID(id: Int): Product? {
//        return productList.value.firstOrNull{
//            it.id == id
//        }
//    }
//
//}