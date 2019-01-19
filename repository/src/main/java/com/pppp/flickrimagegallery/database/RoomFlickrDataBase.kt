package com.pppp.flickrimagegallery.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pppp.flickrimagegallery.dao.FlickrDao
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage

@Database(entities = [RoomFlickrImage::class], version = 1)
internal abstract class RoomFlickrDataBase : RoomDatabase(), FlickrDatabase {
    override abstract fun dao(): FlickrDao
}

