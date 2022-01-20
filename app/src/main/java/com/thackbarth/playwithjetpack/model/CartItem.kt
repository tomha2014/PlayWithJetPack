package com.thackbarth.playwithjetpack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_Cart" )
data class CartItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id" )
    val id: Int = 0,

    @ColumnInfo(name = "product_ID" )
    val productID : Int,

    @ColumnInfo(name = "quantity" )
    val quantity: Int = 0
)
