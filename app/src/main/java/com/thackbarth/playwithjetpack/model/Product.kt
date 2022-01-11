package com.thackbarth.playwithjetpack.model

/*

https://fakestoreapi.com/products

    {
       "id":1,
       "title":"Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
       "price":109.95,
       "description":"Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
       "category":"men's clothing",
       "image":"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
       "rating":{
          "rate":3.9,
          "count":120
       }
    },
 */

data class Rating(
    val rate: Float,
    val count: Int
)

data class Product(
    val id: Int,
    val title:String,
    val price: Float,
    val description: String,
    val category: String,
    val image: String,
    val rating:Rating
)
