package com.thackbarth.playwithjetpack.repos

import com.thackbarth.playwithjetpack.model.DataOrException
import com.thackbarth.playwithjetpack.model.Product
import com.thackbarth.playwithjetpack.network.StoreApi
import javax.inject.Inject



class StoreRepo @Inject constructor(private val api:StoreApi){


    suspend fun getListOfProducts():DataOrException< List<Product>, Boolean, Exception> {

        val response = try{
            api.get()
        } catch (e:java.lang.Exception){
            return DataOrException(e=e)
        }
        return DataOrException(response)
    }
}