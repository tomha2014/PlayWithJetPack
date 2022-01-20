package com.thackbarth.playwithjetpack.data


import androidx.compose.runtime.MutableState
import androidx.room.*
import com.thackbarth.playwithjetpack.Constants
import com.thackbarth.playwithjetpack.Constants.TABLE_NAME
import com.thackbarth.playwithjetpack.model.CartItem
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDatabaseDao {

// Product List
    @Query("SELECT * from $TABLE_NAME")
    fun getProducts():
            Flow<List<Product>>

    @Query("SELECT * from $TABLE_NAME where id =:id")
    suspend fun getProductById(id: String): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: Product)

    @Query("DELETE from $TABLE_NAME")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteProduct(product: Product)

// Shopping cart

    @Query("SELECT * from ${Constants.CART_TABLE_NAME}")
    fun getCartItems():
            Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item: CartItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCartItem(item: CartItem)

    @Query("DELETE from ${Constants.CART_TABLE_NAME}")
    suspend fun cartDeleteAllCartItems()

    @Delete
    suspend fun deleteCartItem(item: CartItem)

    @Query("SELECT COUNT(*) from ${Constants.CART_TABLE_NAME}")
    fun countOfCartItems(): Int

}
