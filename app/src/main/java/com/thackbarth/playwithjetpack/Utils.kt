package com.thackbarth.playwithjetpack

import androidx.compose.runtime.collectAsState
import com.thackbarth.playwithjetpack.model.Product

fun filterListForString(searchString:String,everything:String, lst: List<Product>) : List<Product>{

    return lst.filter {
        if (searchString.uppercase().trim().equals(everything.uppercase().trim())) {
            true
        }else {
            it.category == searchString
        }
    }

}