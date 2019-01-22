package com.pppp.flickrimagegallery

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.repository.FlickrRepository
import com.pppp.network.client.logger.Logger
import com.pppp.uscases.main.events.Effect
import com.pppp.uscases.main.events.Event
import io.mockk.CapturingSlot
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Unconfined
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

const val TEXT = "text"

@ExtendWith(MockKExtension::class)
internal class FlickrRepositoryUseCaseTest {
    private lateinit var useCase: RepositoryUseCase
    @MockK(relaxed = true)
    private lateinit var handler: (Event) -> Unit
    @MockK
    private lateinit var deferred: Deferred<Feed>
    @MockK
    private lateinit var feed: Feed
    private var images: List<FlickrImage> = emptyList()
    private val eventcaptor: CapturingSlot<Event.LoadComplete> = slot()
    @MockK
    private lateinit var repo: FlickrRepository
    @MockK(relaxed = true)
    private lateinit var logger: Logger

    @BeforeEach
    internal fun setUp() {
        useCase = RepositoryUseCase(repo, logger, Unconfined, Unconfined)
        coEvery { repo.getPics() } returns images
        coEvery { deferred.await() } returns feed
        every { feed.images } returns images
        useCase.execute(Effect.GetAllImages, handler)
    }

    @Test
    internal fun `when starts gets feeds`() {
        coVerify(exactly = 1) { repo.getPics() }
        confirmVerified(repo)
    }

    @Test
    internal fun `when gets feeds sends them`() {
        verify(exactly = 1) { handler(capture(eventcaptor)) }
        confirmVerified(handler)
    }

    @Test
    internal fun `sends then sends the result`() {
        verify(exactly = 1) { handler(capture(eventcaptor)) }
        assertEquals(images, eventcaptor.captured.images)
        confirmVerified(handler)
    }

    @Test
    internal fun `error then sends error`() {
        // GIVEN
        coEvery { repo.getPics() } throws Exception()
        val handler: (Event) -> Unit = mockk()
        every { handler.invoke(any()) } just Runs
        // WHEN
        useCase.execute(Effect.GetAllImages, handler)
        // THEN
        verify(exactly = 1) { handler(ofType(Event.Error::class)) }
        confirmVerified(handler)
    }

    @Test
    internal fun `error then logs`() {
        // GIVEN
        coEvery { repo.getPics() } throws Exception(TEXT)
        val handler: (Event) -> Unit = mockk()
        every { handler.invoke(any()) } just Runs
        // WHEN
        useCase.execute(Effect.GetAllImages, handler)
        // THEN
        verify(exactly = 1) { logger.w(ofType(), ofType()) }
        confirmVerified(logger)
    }
}