package com.thackbarth.playwithjetpack.di

import android.content.Context
import androidx.room.Room
import com.thackbarth.playwithjetpack.data.PhotoDatabase
import com.thackbarth.playwithjetpack.data.PhotoDatabaseDoa
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
    fun providePhotoDoa(photoDatabase: PhotoDatabase): PhotoDatabaseDoa
    = photoDatabase.photoDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : PhotoDatabase
    = Room.databaseBuilder(
        context,
        PhotoDatabase::class.java,
        "photos.db")
        .fallbackToDestructiveMigration()
        .build()

}