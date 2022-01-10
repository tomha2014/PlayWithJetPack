package com.thackbarth.playwithjetpack.network


import com.thackbarth.playwithjetpack.model.Photo
import com.thackbarth.playwithjetpack.model.PhotoNetworkEntity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PhotoApi {

    @GET("photos")
    suspend fun get():List<Photo>

    companion object {
        var apiService: PhotoApi? = null
        fun getInstance() : PhotoApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PhotoApi::class.java)
            }
            return apiService!!
        }
    }
}