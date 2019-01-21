package com.pppp.flickrimagegallery

import android.content.Context
import com.pppp.flickrimagegallery.application.di.AppModule
import com.pppp.flickrimagegallery.application.di.DaggerAppComponentImpl
import com.pppp.uscases.Effect
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DependenciesTest {
    @MockK
    lateinit var context: Context

    @Test
    internal fun `all usecases are injected`() {
        val appComponent = DaggerAppComponentImpl.builder().appModule(AppModule(context)).build()
        val uscecases = appComponent.useCases()
        val showDetailUseCase = uscecases[Effect.ShowDetail::class.java]
        assertNotNull(showDetailUseCase)
        val getImagesUseCase = uscecases[Effect.GetAllImages::class.java]
        assertNotNull(getImagesUseCase)
    }

}