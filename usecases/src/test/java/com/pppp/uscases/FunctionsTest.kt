package com.pppp.uscases

import com.pppp.entites.FlickrImage
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
internal class FunctionsTest {
    private val images: List<FlickrImage> = mockk()
    private val event: Event.LoadComplete = mockk()
    private val model: Model = mockk()

    @BeforeEach
    internal fun setUp() {
        every { event.images } returns images
    }

    @Nested
    inner class Update {
        @Test
        internal fun `when complete then returns model complete`() {
            val model = update(Model.Complete(images), event).modelUnsafe()
            assertThat(model).isInstanceOf(Model.Complete::class.java)
        }

        @Test
        internal fun `when complete then model returns results`() {
            val model = update(Model.Complete(images), event).modelUnsafe() as Model.Complete
            assertEquals(images, model.result)
        }

        @Test
        internal fun `when complete then effect is got images`() {
            val effects = update(Model.Complete(images), event).effects()
            val effect = effects.first()
            assertThat(effect).isInstanceOf(Effect.GotImages::class.java)
        }

        @Test
        internal fun `when complete then effect contains images`() {
            val effects = update(Model.Complete(images), event).effects()
            val images = (effects.first() as Effect.GotImages).images
            assertEquals(this@FunctionsTest.images, images)
        }

        @Test
        internal fun `when else no model change`() {
            val event: Event = mockk()
            val model = update(model, event)
            assertFalse(model.hasModel(), "No model change")
        }

        @Test
        internal fun `when else no effects change`() {
            val event: Event = mockk()
            val model = update(model, event)
            assertFalse(model.hasEffects(), "No effects change")
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