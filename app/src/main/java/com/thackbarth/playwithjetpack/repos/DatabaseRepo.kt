package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DatabaseRepo @Inject constructor(private val productDatabaseDao: ProductDatabaseDao) {

    suspend fun addNote(product: Product) = productDatabaseDao.insert(product)
    suspend fun updateNote(product: Product) = productDatabaseDao.update(product)
    suspend fun deleteNote(product: Product) = productDatabaseDao.deleteProduct(product)
    suspend fun deleteAllNotes() = productDatabaseDao.deleteAll()
    fun getAllNotes(): Flow<List<Product>> = productDatabaseDao.getProducts().flowOn(Dispatchers.IO)
        .conflate()

}