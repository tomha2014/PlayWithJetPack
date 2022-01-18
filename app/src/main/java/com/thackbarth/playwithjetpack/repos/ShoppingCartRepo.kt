package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.data.ShoppingCartDao
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import javax.inject.Inject

class ShoppingCartRepo @Inject constructor(private val dao: ShoppingCartDao){

    suspend fun addItem(item: CartItem) = dao.insert(item)
    suspend fun updateItem(item: CartItem) = dao.update(item)
    suspend fun deleteItem(item: CartItem) = dao.deleteItem(item)
//    suspend fun count() : Int{
//        return  dao.count()
//    }

}