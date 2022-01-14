package com.thackbarth.playwithjetpack

import com.thackbarth.playwithjetpack.model.Product

fun filterListForString(searchString:String, everything:String, lst: List<Product>) : List<Product>{
    return lst.filter {
        if (searchString.uppercase().trim().equals(everything.uppercase().trim())) {
            true
        }else {
            it.category == searchString
        }
    }
}

fun getCategoryIndex(cats:List<String>, searchString:String) : Int {
    return if (searchString.isNullOrEmpty() || (searchString.trim() == Constants.EVERYTHING.trim())) {
        0
    } else {
        cats.indexOf(searchString)
    }
}