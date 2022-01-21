package com.thackbarth.playwithjetpack.navigation.screens.homeScreen

import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.flow.Flow


class MockDatabaseDao : ProductDatabaseDao {
    override fun getProducts(): Flow<List<Product>> {

        TODO("Not yet implemented")
    }

    override suspend fun getProductById(id: String): Product {
        TODO("Not yet implemented")
    }

    override suspend fun insert(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun update(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override fun getCartItems(): Flow<List<CartItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCartItem(item: CartItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCartItem(item: CartItem) {
        TODO("Not yet implemented")
    }

    override suspend fun cartDeleteAllCartItems() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCartItem(item: CartItem) {
        TODO("Not yet implemented")
    }

    override fun countOfCartItems(): Int {
        TODO("Not yet implemented")
    }

}