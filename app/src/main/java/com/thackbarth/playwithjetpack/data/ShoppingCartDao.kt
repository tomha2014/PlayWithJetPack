package com.thackbarth.playwithjetpack.data


import androidx.room.*
import com.thackbarth.playwithjetpack.Constants.CART_TABLE_NAME
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {

    @Query("SELECT * from $CART_TABLE_NAME")
    fun getItems():
            Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(item: CartItem)

    @Query("DELETE from $CART_TABLE_NAME")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteItem(item: CartItem)

//    @Query("SELECT COUNT(*) FROM $CART_TABLE_NAME")
//    suspend fun count(): Int
}
