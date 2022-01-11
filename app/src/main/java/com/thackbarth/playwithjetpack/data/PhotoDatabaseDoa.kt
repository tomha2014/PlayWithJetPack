package com.thackbarth.playwithjetpack.data

import androidx.room.*
import com.thackbarth.playwithjetpack.model.Photo
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDatabaseDoa {

    @Query("SELECT * from photo_table")
    fun getPhotos(): Flow<List<Photo>>

    @Query("SELECT * from photo_table where id=:id")
    suspend fun getPhotoById(id:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(photo: Photo)

    @Query("DELETE  from photo_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deletePhoto(photo: Photo)

}
