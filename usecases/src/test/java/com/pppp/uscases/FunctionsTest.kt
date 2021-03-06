package com.pppp.uscases

import com.pppp.entites.FlickrImage
import com.pppp.uscases.main.events.Detail
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import com.pppp.uscases.main.events.Model
import com.pppp.uscases.main.init
import com.pppp.uscases.main.update
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith

private const val POSITION = 1

@ExtendWith(MockKExtension::class)
@TestInstance(PER_CLASS)
internal class FunctionsTest {
    @MockK
    private lateinit var images: List<FlickrImage>
    @MockK
    private lateinit var event: Event.LoadComplete
    @MockK
    private lateinit var model: Model
    @MockK
    private lateinit var modelComplete: Model.Complete
    @MockK(relaxed = true)
    private lateinit var detailSelected: Event.DetailSelected
    @MockK(relaxed = true)
    private lateinit var showDetail: Event.ShowDetail
    @MockK(relaxed = true)
    private lateinit var navigateToDetailModel: Model.NavigateToDetail
    @MockK(relaxed = true)
    private lateinit var detail: Detail
    @MockK(relaxed = true)
    private lateinit var warning: Model.Warning

    @BeforeEach
    internal fun setUp() {
        clearMocks(detail)
        every { event.images } returns images
        every { detailSelected.detail } returns detail
        every { showDetail.detail } returns detail
        every { navigateToDetailModel.previousState } returns model
        every { warning.previousState } returns model
    }

    @Nested
    inner class `update` {
        @Test
        internal fun `when complete then returns model complete`() {
            val model = update(
                Model.Complete(images),
                event
            ).modelUnsafe()
            assertThat(model).isInstanceOf(Model.Complete::class.java)
        }

        @Test
        internal fun `when complete then model returns results`() {
            val model = update(
                Model.Complete(images),
                event
            ).modelUnsafe() as Model.Complete
            assertEquals(images, model.result)
        }

        @Test
        internal fun `when complete then effect is got images`() {
            val effects = update(
                Model.Complete(images),
                event
            ).effects()
            val effect = effects.first()
            assertThat(effect).isInstanceOf(Effect.GotImages::class.java)
        }

        @Test
        internal fun `when complete then effect contains images`() {
            val effects = update(
                Model.Complete(images),
                event
            ).effects()
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


        @Nested
        inner class `show detail` {

            @Test
            internal fun `when show details and image loaded then navigate to detail`() {
                val effect = update(modelComplete, detailSelected).effects().first()
                assertThat(effect).isInstanceOf(Effect.ShowDetail::class.java)
            }

            @Test
            internal fun `when show details and image loaded gets right data`() {
                val next = update(modelComplete, detailSelected)
                assertEquals(modelComplete, next.modelUnsafe())
                val effect = next.effects().first()
                assertThat(effect).isInstanceOf(Effect.ShowDetail::class.java)
            }

            @Test
            internal fun `when show detail then navigates to detail`() {
                val model = update(model, showDetail).modelUnsafe()
                assertThat(model).isInstanceOf(Model.NavigateToDetail::class.java)
            }

            @Test
            internal fun `when show detail then gets the right data`() {
                val model = update(
                    model,
                    showDetail
                ).modelUnsafe() as Model.NavigateToDetail
                assertEquals(detail, model.detail)
                assertEquals(this@FunctionsTest.model, model.previousState)
            }
        }
    }

    @Nested
    inner class `init` {

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

        @Test
        internal fun `when show detail then goes back`() {
            val actual = init(navigateToDetailModel).model()
            assertEquals(model, actual)
        }

        @Test
        internal fun `when warning applies previous`() {
            val actual = init(warning).model()
            assertEquals(model, actual)
        }
    }
}