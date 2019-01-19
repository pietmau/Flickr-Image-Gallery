package com.pppp.flickrimagegallery

import com.pppp.entites.Feed
import com.pppp.entites.FlickrImage
import com.pppp.flickrimagegallery.database.FlickrDataBase
import com.pppp.flickrimagegallery.database.insert
import com.pppp.flickrimagegallery.mapper.Mapper
import com.pppp.flickrimagegallery.pokos.RoomFlickrImage
import com.pppp.network.api.Client
import com.pppp.network.poko.RetrofitFlickrImage
import com.pppp.uscases.Event
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Unconfined
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(PER_CLASS)
internal class RepositoryUseCaseTest {
    @MockK()
    private lateinit var client: Client
    @MockK(relaxed = true)
    lateinit var database: FlickrDataBase
    @MockK
    private lateinit var mapper: Mapper<FlickrImage, RoomFlickrImage>
    private lateinit var useCase: RepositoryUseCase
    @MockK(relaxed = true)
    private lateinit var handler: (Event) -> Unit
    @MockK
    private lateinit var deferred: Deferred<Feed>
    @MockK
    private lateinit var feed: Feed
    @MockK
    private lateinit var retrofitFlickrImage: RetrofitFlickrImage
    @MockK
    private lateinit var roomFlickrImage: RoomFlickrImage
    private var retrofitImages: List<RetrofitFlickrImage> = emptyList()
    private val eventcaptor: CapturingSlot<Event.LoadComplete> = slot()

    @BeforeEach
    internal fun setUp() {
        clearMocks(handler, mapper, database)
        useCase = RepositoryUseCase(client, database, mapper, Unconfined, Unconfined)
        every { client.getPics() } returns deferred
        coEvery { deferred.await() } returns feed
        every { feed.entry } returns retrofitImages
        every { mapper.map(any()) } returns roomFlickrImage
        every { database.insert(any()) } just Runs
        retrofitImages = listOf<RetrofitFlickrImage>(retrofitFlickrImage)
        useCase.execute(handler)
    }

    @Test
    internal fun `when starts gets feeds`() {
        verify(exactly = 1) { client.getPics() }
        confirmVerified(client)
    }

    @Test
    internal fun `when gets feeds maps them`() {
        verify(exactly = 1) { mapper.map(retrofitFlickrImage) }
        confirmVerified(mapper)
    }

    @Test
    internal fun `when gets feeds saves them`() {
        verify(exactly = 1) { database.insert(any()) }
        confirmVerified(database)
    }

    @Test
    internal fun `when gets feeds sends them`() {
        verify(exactly = 1) { handler(capture(eventcaptor)) }
        confirmVerified(handler)
    }

    @Test
    internal fun `sends them sends the result`() {
        verify(exactly = 1) { handler(capture(eventcaptor)) }
        assertEquals(retrofitImages, eventcaptor.captured.images)
    }

    @Test
    internal fun `error them sends error`() {
        // GIVEN
        every { client.getPics() } throws Exception()
        val handler: (Event) -> Unit = mockk()
        every { handler.invoke(any()) } just Runs
        // WHEN
        useCase.execute(handler)
        // THEN
        verify(exactly = 1) { handler(ofType(Event.Error::class)) }
        confirmVerified(handler)
    }
}