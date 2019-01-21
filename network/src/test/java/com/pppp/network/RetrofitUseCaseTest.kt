package com.pppp.network

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.network.api.Client
import com.pppp.network.utils.AndroidLogger
import com.pppp.uscases.Effect
import com.pppp.uscases.Event
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.async
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.coroutines.CoroutineContext

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RetrofitUseCaseTest : CoroutineScope {
    private val TEXT = "text"
    override val coroutineContext: CoroutineContext = Unconfined
    @MockK
    private lateinit var client: Client
    private lateinit var useCase: RetrofitUseCase
    @MockK
    private lateinit var handler: (Event) -> Unit
    @MockK
    private lateinit var feed: Feed
    @MockK
    private lateinit var entries: List<FlickrImage>
    @MockK(relaxed = true)
    private lateinit var logger: AndroidLogger
    private val slot: CapturingSlot<Event> = slot()
    @MockK(relaxed = true)
    private lateinit var effect: Effect.GetAllImages

    @BeforeEach
    internal fun setUp() {
        useCase = RetrofitUseCase(client, logger, Unconfined)
        every { client.getPics() } returns async { feed }
        every { feed.entry } returns entries
        every { handler.invoke(capture(slot)) } just Runs
        slot.clear()
    }

    @Test
    internal fun `when successful return calls bacl`() {
        // WHEN
        useCase.execute(effect, handler)
        // THEN
        verify { handler.invoke(any()) }
        confirmVerified(handler)
    }

    @Test
    internal fun `when successful return result`() {
        // WHEN
        useCase.execute(effect, handler)
        // THEN
        val captured = slot.captured as Event.LoadComplete
        val images = captured.images
        assertEquals(entries, images)
    }

    @Test
    internal fun `when exception then returns error`() {
        // GIVEN
        every { client.getPics() } answers { throw Exception(TEXT) }
        // WHEN
        useCase.execute(effect, handler)
        // THEN
        val captured = slot.captured as Event.Error
        assertEquals(TEXT, captured.throwable.message)
    }

    @Test
    internal fun `when exception then logs`() {
        // GIVEN
        every { client.getPics() } answers { throw Exception(TEXT) }
        // WHEN
        useCase.execute(effect, handler)
        // THEN
        val captured = slot.captured as Event.Error
        assertEquals(TEXT, captured.throwable.message)
    }
}