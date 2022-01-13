package com.thackbarth.playwithjetpack.data


import androidx.compose.runtime.MutableState
import androidx.room.*
import com.thackbarth.playwithjetpack.Constants.TABLE_NAME
import com.thackbarth.playwithjetpack.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDatabaseDao {

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
}
