package com.pppp.flickrimagegallery.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pppp.flickrimagegallery.database.dao.FlickrDao
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage

@Database(entities = [RoomFlickrImage::class], version = 1)
internal abstract class FlickrDataBase : RoomDatabase() {
    abstract fun dao(): FlickrDao
}

internal fun FlickrDataBase.insert(images: List<RoomFlickrImage>) { // TODO introduce a new class instead
    dao().insert(images)
}

internal fun FlickrDataBase.getLatestImages(limit: Int) = dao().getImages(limit)
