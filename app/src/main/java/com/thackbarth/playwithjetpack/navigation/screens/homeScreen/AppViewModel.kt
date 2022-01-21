package com.thackbarth.playwithjetpack.navigation.screens.homeScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppViewModel
@Inject
constructor(
    private val repository: DatabaseRepo,
    private val storeRepo: StoreRepo
) : ViewModel() {

    var filterCategory: String by mutableStateOf(Constants.EVERYTHING)
    var filterItemIndex: Int by mutableStateOf(0)
    var productList = MutableStateFlow<List<Product>>(emptyList())
    val cartList = MutableStateFlow<List<CartItem>>(emptyList())

    private var errorMessage: String by mutableStateOf("")
    val categoryList = MutableLiveData<List<String>>(emptyList())
    var cartSize: Int by mutableStateOf(0)

    init {
//        Log.d(Constants.TAG, "init")
        loadShoppingCart()
        loadAllData()
    }

    private fun loadShoppingCart() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllItems().distinctUntilChanged().collect { stuff ->
                if (!stuff.isNullOrEmpty()) {
                    cartList.value = stuff
                }
            }
        }
    }

    fun addProductToShoppingCart(productId: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            // if cartitem exists bump up the quantity by 1.
            // Not good logic as the user may want to add 2,
            // but this is just a exercise application

            val item = cartList.value.firstOrNull{
                it.productID == productId
            }

            if (item == null) {
                repository.addItem(CartItem(productID = productId))
            }else{
                repository.updateItem(CartItem(item.id, item.productID, item.quantity+1))
            }
        }
        // refresh the cart
        loadShoppingCart()
    }


    fun loadAllData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllProducts1().distinctUntilChanged().collect {
                    stuff ->
                if (stuff.isNullOrEmpty()){
                    if (productList.value.isEmpty()){
//                        Log.d(Constants.TAG, "database was empty of stuff, go get new stuff from server")
                        try {

                            val products = storeRepo.getListOfProducts().data
                            if (products != null) {
//                                Log.d(Constants.TAG, "data Loaded")


                                // Save each item in the database for next time.
                                products.forEach {
                                    repository.addProduct(it)
                                }
                                productList.value = products

                                categoryList.postValue( buildCategoryList(stuff) )
                            }
                        } catch (e: Exception) {
                            errorMessage = e.message.toString()
                        }
                    }
                } else {
                    productList.value = stuff
                    categoryList.postValue( buildCategoryList(stuff) )
                }
            }
        }
    }

    fun buildCategoryList(lst: List<Product>) : List<String>{

        val cats = ArrayList<String>()
        cats.add("Everything")
        lst.forEach{
            if (!cats.contains(it.category)){
                cats.add(it.category)
            }
        }

        return cats
    }

    fun findProductByID(id: Int, productList: List<Product>): Product? {
        return productList.first { it.id == id }
    }
}