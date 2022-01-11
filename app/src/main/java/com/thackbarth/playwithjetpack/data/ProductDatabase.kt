package com.thackbarth.playwithjetpack.data



import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thackbarth.playwithjetpack.model.Product


@Database(entities = [Product::class], version = 1, exportSchema = false)

abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDatabaseDao
}