package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


//class ShoppingCartRepo @Inject constructor(private val dao: ShoppingCartDao){
//
//    fun getAllItems(): Flow<List<CartItem>> = dao.getItems().flowOn(Dispatchers.IO)
//        .conflate()
//    suspend fun addItem(item: CartItem) = dao.insert(item)
//    suspend fun updateItem(item: CartItem) = dao.update(item)
//    suspend fun deleteAllItems() = dao.deleteAll()
//    suspend fun deleteItem(item: CartItem) = dao.deleteItem(item)
//
//    suspend fun getCount():Int{
//        return dao.count()
//    }
//
//
//}

