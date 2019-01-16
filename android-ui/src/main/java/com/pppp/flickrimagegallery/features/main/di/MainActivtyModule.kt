package com.pppp.flickrimagegallery.features.main.di

import com.pppp.flickrimagegallery.features.main.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@Module(subcomponents = [MainSubComponent::class])
abstract class MainActivtyModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: MainSubComponent.Builder): AndroidInjector.Factory<*>
}