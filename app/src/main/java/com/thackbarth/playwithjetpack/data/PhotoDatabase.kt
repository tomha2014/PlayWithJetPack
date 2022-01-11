package com.thackbarth.playwithjetpack.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thackbarth.playwithjetpack.model.Photo

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase:RoomDatabase() {
    abstract  fun photoDao():PhotoDatabaseDoa
}