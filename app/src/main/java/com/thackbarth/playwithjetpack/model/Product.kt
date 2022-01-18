package com.thackbarth.playwithjetpack.model

import androidx.room.*
import java.util.*

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



@Entity(tableName = "Product_table" )
data class Product(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "product_title" )
    val title:String,

    @ColumnInfo(name = "product_price" )
    val price: Float,

    @ColumnInfo(name = "product_description" )
    val description: String,

    @ColumnInfo(name = "product_category" )
    val category: String,

    @ColumnInfo(name = "product_image_url" )
    val image: String//,

//    val rating:Rating
)
