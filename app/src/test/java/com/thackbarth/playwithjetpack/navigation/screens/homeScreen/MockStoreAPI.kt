package com.thackbarth.playwithjetpack.navigation.screens.homeScreen

import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.network.StoreApi

class MockStoreAPI: StoreApi {
    override suspend fun get(): List<Product> {
        TODO("Not yet implemented")
    }

}