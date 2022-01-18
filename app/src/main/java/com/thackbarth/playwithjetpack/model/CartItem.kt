package com.thackbarth.playwithjetpack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_Cart" )
data class CartItem(

    @PrimaryKey
    @ColumnInfo(name = "id" )
    val id: Int,

    @ColumnInfo(name = "product_ID" )
    val productID : Int,

    @ColumnInfo(name = "quantity" )
    val quantity: Int = 0
)
