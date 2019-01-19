package com.pppp.flickrimagegallery.setup

import com.pppp.flickrimagegallery.features.main.view.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [TestSubComponent::class])
abstract class TestActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: TestSubComponent.Builder): AndroidInjector.Factory<*>
}