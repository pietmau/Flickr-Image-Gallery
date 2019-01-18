package com.pppp.uscases

import com.pppp.entites.FlickrImage
import io.mockk.mockk
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
internal class FunctionsTest {
    private val images: List<FlickrImage> = mockk()
    private val event: Event = mockk()
    private val model: Model = mockk()

    @Nested
    inner class Update {
        @Test
        internal fun `when complete then returns complete`() {
            val model = update(Model.Complete(images), event).modelUnsafe()
            assertThat(model).isInstanceOf(Model.Complete::class.java)
        }

        @Test
        internal fun `when complete then returns results`() {
            val model = update(Model.Complete(images), event).modelUnsafe() as Model.Complete
            assertEquals(images, model.result)
        }

        @Test
        internal fun `when else returns same model`() {
            val model = update(model, event).modelUnsafe()
            assertEquals(this@FunctionsTest.model, model)
        }
    }

    @Nested
    inner class Init {

        @Test
        internal fun `when starting then model is loading`() {
            val model = init(Model.Starting).model()
            assertEquals(Model.Loading, model)
        }

        @Test
        internal fun `when not starting then model is the same`() {
            val model = init(model).model()
            assertEquals(this@FunctionsTest.model, model)
        }

        @Test
        internal fun `when starting then effect is get`() {
            val effect = init(Model.Starting).effects().toList().first()
            assertEquals(Effect.GetAllImages, effect)
        }
    }

}