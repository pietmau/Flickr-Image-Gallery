package com.pppp.flickrimagegallery.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage


@Dao
internal abstract class FlickrDao {

    @Query("SELECT * from roomflickrimage ORDER BY timestamp LIMIT :limit")
    internal abstract fun getImages(limit: Int): List<RoomFlickrImage>

    @Query("SELECT * from roomflickrimage WHERE id = :id")
    internal abstract fun getImageById(id: String): RoomFlickrImage?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    internal abstract fun insert(items: List<RoomFlickrImage>)
}