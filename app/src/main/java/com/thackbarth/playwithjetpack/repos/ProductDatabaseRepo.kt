package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class ProductDatabaseRepo @Inject constructor(private val productDatabaseDao: ProductDatabaseDao) {

    suspend fun addProduct(product: Product) = productDatabaseDao.insert(product)
    suspend fun updateProduct(product: Product) = productDatabaseDao.update(product)
    suspend fun deleteProduct(product: Product) = productDatabaseDao.deleteProduct(product)
    suspend fun deleteAllProducts() = productDatabaseDao.deleteAll()
    fun getAllProducts1(): Flow<List<Product>> = productDatabaseDao.getProducts().flowOn(Dispatchers.IO)
        .conflate()

}