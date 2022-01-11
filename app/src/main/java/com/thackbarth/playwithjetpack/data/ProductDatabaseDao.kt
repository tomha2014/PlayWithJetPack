package com.thackbarth.playwithjetpack.data


import androidx.compose.runtime.MutableState
import androidx.room.*
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDatabaseDao {

    @Query("SELECT * from product_table")
    fun getProducts():
            Flow<List<Product>>

    @Query("SELECT * from product_table where id =:id")
    suspend fun getProductById(id: String): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(product: Product)

    @Query("DELETE from product_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteProduct(product: Product)
}
