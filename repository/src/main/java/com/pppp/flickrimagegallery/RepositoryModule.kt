package com.pppp.flickrimagegallery

import android.content.Context
import androidx.room.Room
import com.pppp.flickrimagegallery.database.FlickrDatabase
import com.pppp.flickrimagegallery.database.RoomFlickrDataBase
import com.pppp.flickrimagegallery.mapper.MapperImpl
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage
import com.pppp.flickrimagegallery.repository.Repository
import com.pppp.flickrimagegallery.repository.RoomRepository
import com.pppp.network.api.Client
import com.pppp.uscases.Effect
import com.pppp.uscases.di.EffectKey
import com.pppp.uscases.usecases.UseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object RepositoryModule {
    @JvmStatic
    @Provides
    internal fun provideRepo(database: FlickrDatabase<RoomFlickrImage>): Repository =
        RoomRepository(database)

    @JvmStatic
    @Provides
    internal fun provideDb(context: Context): FlickrDatabase<RoomFlickrImage> = TODO()


    @JvmStatic
    @Provides
    @IntoMap
    @EffectKey(Effect.GetAllImages::class)
    fun provideUseCase(client: Client, context: Context): UseCase =
        RepositoryUseCase(
            client = client,
            database = Room.databaseBuilder(
                context,
                RoomFlickrDataBase::class.java,
                "comics"
            ).build(),
            mapper = MapperImpl()
        )
}