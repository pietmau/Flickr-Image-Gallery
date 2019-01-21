package com.pppp.flickrimagegallery

import android.content.Context
import androidx.room.Room
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.mapper.MapperImpl
import com.pppp.flickrimagegallery.repository.FlickrRepository
import com.pppp.flickrimagegallery.repository.FlickrRepositoryImpl
import com.pppp.network.api.Client
import com.pppp.network.utils.AndroidLogger
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
    @IntoMap
    @EffectKey(Effect.GetAllImages::class)
    fun provideUseCase(repo: FlickrRepository): UseCase<Effect> =
        RepositoryUseCase(repo, AndroidLogger()) as UseCase<Effect>

    @Provides
    @JvmStatic
    internal fun repo(client: Client, db: FlickrDataBase): FlickrRepository =
        FlickrRepositoryImpl(client, db, MapperImpl(), AndroidLogger())

    @Provides
    @JvmStatic
    internal fun db(context: Context): FlickrDataBase =
        Room.databaseBuilder(context, FlickrDataBase::class.java, "images").build()
}