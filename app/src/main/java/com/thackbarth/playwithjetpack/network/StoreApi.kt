package com.thackbarth.playwithjetpack.network


import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface StoreApi {

    @GET("products")
    suspend fun get():List<Product>

    companion object {
        var apiService: StoreApi? = null

        fun getInstance() : StoreApi {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(StoreApi::class.java)
            }
            return apiService!!
        }
    }
}