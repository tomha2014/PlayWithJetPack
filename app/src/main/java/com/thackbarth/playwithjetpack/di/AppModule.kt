package com.thackbarth.playwithjetpack.di

import android.content.Context
import androidx.room.Room
import com.thackbarth.playwithjetpack.data.ProductDatabase
import com.thackbarth.playwithjetpack.data.ProductDatabaseDao
import com.thackbarth.playwithjetpack.model.Product
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Singleton
    @Provides
    fun provideProductDao(productDatabase: ProductDatabase): ProductDatabaseDao
            = productDatabase.productDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ProductDatabase
            = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        "Product_table")
        .fallbackToDestructiveMigration()
        .build()


}