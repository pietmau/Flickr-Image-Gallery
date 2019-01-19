package com.pppp.flickrimagegallery.database

import com.pppp.flickrimagegallery.dao.FlickrDao

internal interface FlickrDatabase {
    fun dao(): FlickrDao
}
