package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DatabaseRepo @Inject constructor(private val productDatabaseDao: ProductDatabaseDao) {

    // Products
    suspend fun addProduct(product: Product) = productDatabaseDao.insert(product)
    suspend fun updateProduct(product: Product) = productDatabaseDao.update(product)
    suspend fun deleteProduct(product: Product) = productDatabaseDao.deleteProduct(product)
    suspend fun deleteAllProducts() = productDatabaseDao.deleteAll()
    fun getAllProducts1(): Flow<List<Product>> = productDatabaseDao.getProducts().flowOn(Dispatchers.IO)
        .conflate()



    // shopping cart

    fun getAllItems(): Flow<List<CartItem>> = productDatabaseDao.getCartItems().flowOn(Dispatchers.IO)
        .conflate()
    suspend fun addItem(item: CartItem) = productDatabaseDao.insertCartItem(item)
    suspend fun updateItem(item: CartItem) = productDatabaseDao.updateCartItem(item)
    suspend fun deleteAllItems() = productDatabaseDao.cartDeleteAllCartItems()
    suspend fun deleteItem(item: CartItem) = productDatabaseDao.deleteCartItem(item)

    suspend fun countOfCartItems():Int{
        return productDatabaseDao.countOfCartItems()
    }

}