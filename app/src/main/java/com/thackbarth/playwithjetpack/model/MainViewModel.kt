package com.thackbarth.playwithjetpack.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thackbarth.playwithjetpack.network.PhotoApi
import kotlinx.coroutines.launch

object MainViewModel : ViewModel() {

    public var photoListResponse:List<Photo> by mutableStateOf(listOf())

    var errorMessage: String by mutableStateOf("")

    fun getMovieList() {
        viewModelScope.launch {
            val apiService = PhotoApi.getInstance()
            try {
                val photoList = apiService.get()
                photoListResponse = photoList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}