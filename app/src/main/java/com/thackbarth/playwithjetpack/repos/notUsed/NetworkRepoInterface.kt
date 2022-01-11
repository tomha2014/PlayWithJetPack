package com.thackbarth.playwithjetpack.repos.notUsed

import com.thackbarth.playwithjetpack.model.Product



interface NetworkRepoInterface {
    suspend fun get():List<Product>
}