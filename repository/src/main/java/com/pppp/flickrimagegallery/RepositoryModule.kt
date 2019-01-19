package com.pppp.flickrimagegallery

import android.content.Context
import androidx.room.Room
import com.pppp.flickrimagegallery.database.RoomFlickrDataBase
import com.pppp.flickrimagegallery.repository.Repository
import com.pppp.flickrimagegallery.repository.RoomRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {
    @JvmStatic
    @Provides
    fun provideRepo(context: Context): Repository {
        val db = Room.databaseBuilder(context, RoomFlickrDataBase::class.java, "comics").build()
        return RoomRepository(db)
    }
}