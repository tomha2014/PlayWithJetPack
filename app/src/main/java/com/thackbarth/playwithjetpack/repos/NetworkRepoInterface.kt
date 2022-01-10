package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.model.Photo


interface NetworkRepoInterface {
    suspend fun get():List<Photo>
}