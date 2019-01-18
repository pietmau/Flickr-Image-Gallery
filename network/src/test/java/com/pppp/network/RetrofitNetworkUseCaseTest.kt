package com.pppp.network

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.network.api.Client
import com.pppp.uscases.Event
import io.mockk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.async
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.coroutines.CoroutineContext

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RetrofitNetworkUseCaseTest : CoroutineScope {
    private val TEXT = "text"
    override val coroutineContext: CoroutineContext = Unconfined
    private val client: Client = mockk()
    private val useCase: RetrofitNetworkUseCase = RetrofitNetworkUseCase(client, Unconfined)
    private val handler: (Event) -> Unit = mockk()
    private val feed: Feed = mockk()
    private val entries: List<FlickrImage> = mockk()
    private val slot: CapturingSlot<Event> = slot()

    @BeforeEach
    internal fun setUp() {
        every { client.getPics() } returns async { feed }
        every { feed.entry } returns entries
        every { handler.invoke(capture(slot)) } just Runs
        slot.clear()
    }

    @Test
    internal fun `when successful return calls bacl`() {
        //WHEN
        useCase.getAllImages(handler)
        // THEN
        verify { handler.invoke(any<Event>()) }
        confirmVerified(handler)
    }

    @Test
    internal fun `when successful return result`() {
        //WHEN
        useCase.getAllImages(handler)
        // THEN
        val captured = slot.captured as Event.LoadComplete
        val images = captured.images
        assertEquals(entries, images)
    }

    @Test
    internal fun `when exception then returns error`() {
        // GIVEN
        every { client.getPics() } answers { throw Exception(TEXT) }
        //WHEN
        useCase.getAllImages(handler)
        // THEN
        val captured = slot.captured as Event.Error
        assertEquals(TEXT, captured.throwable.message)
    }
}