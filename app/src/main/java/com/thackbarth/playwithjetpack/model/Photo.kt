package com.thackbarth.playwithjetpack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class Photo (
    @PrimaryKey
    var id: Int,

    @ColumnInfo(name = "albumId")
    var albumId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "full_size_url")
    var url: String,

    @ColumnInfo(name = "thumb_nail_Url")
    var thumbnailUrl: String,

    )