package com.amary.my.music.data.repository

import com.amary.my.music.data.local.dao.MusicDao
import com.amary.my.music.data.local.entity.ResultEntity
import com.amary.my.music.data.remote.api.MusicApi
import com.amary.my.music.data.remote.response.MusicResponse
import com.amary.my.music.data.remote.response.ResultResponse
import com.amary.my.music.domain.model.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class MusicRepositoryImplTest {

    private lateinit var musicApi: MusicApi
    private lateinit var musicDao: MusicDao
    private lateinit var repository: MusicRepositoryImpl
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        musicApi = mockk()
        musicDao = mockk(relaxed = true)
        repository = MusicRepositoryImpl(musicApi, musicDao, testDispatcher)
    }

    @Test
    fun `searchMusic returns mapped results when response is successful`() = runTest {
        // Given
        val query = "test query"
        val resultResponse = ResultResponse(
            artistId = 1,
            artistName = "Test Artist",
            trackName = "Test Track",
            artworkUrl100 = "https://test.com/image.jpg",
            previewUrl = "https://test.com/preview.mp3"
        )
        val musicResponse = MusicResponse(
            resultCount = 1,
            results = listOf(resultResponse)
        )
        val successResponse = Response.success(musicResponse)

        coEvery { musicApi.searchMusics(query) } returns successResponse

        // When
        val result = repository.searchMusic(query)

        // Then
        assertEquals(1, result.size)
        assertEquals(1, result[0].artistId)
        assertEquals("Test Artist", result[0].artistName)
        assertEquals("Test Track", result[0].trackName)
        coVerify(exactly = 1) { musicApi.searchMusics(query) }
    }

    @Test
    fun `searchMusic returns empty list when response body is null`() = runTest {
        // Given
        val query = "test query"
        val successResponse = Response.success<MusicResponse>(null)

        coEvery { musicApi.searchMusics(query) } returns successResponse

        // When
        val result = repository.searchMusic(query)

        // Then
        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { musicApi.searchMusics(query) }
    }

    @Test
    fun `searchMusic returns empty list when response is not successful`() = runTest {
        // Given
        val query = "test query"
        val errorResponse = Response.error<MusicResponse>(
            404,
            "Not found".toResponseBody()
        )

        coEvery { musicApi.searchMusics(query) } returns errorResponse

        // When
        val result = repository.searchMusic(query)

        // Then
        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { musicApi.searchMusics(query) }
    }

    @Test
    fun `getAll returns mapped results from database`() = runTest {
        // Given
        val resultEntity = ResultEntity(
            artistId = 1,
            artistName = "Test Artist",
            trackName = "Test Track"
        )
        val entities = listOf(resultEntity)

        coEvery { musicDao.getAll() } returns entities

        // When
        val result = repository.getAll()

        // Then
        assertEquals(1, result.size)
        assertEquals(1, result[0].artistId)
        assertEquals("Test Artist", result[0].artistName)
        assertEquals("Test Track", result[0].trackName)
        coVerify(exactly = 1) { musicDao.getAll() }
    }

    @Test
    fun `getAll returns empty list when database is empty`() = runTest {
        // Given
        coEvery { musicDao.getAll() } returns emptyList()

        // When
        val result = repository.getAll()

        // Then
        assertTrue(result.isEmpty())
        coVerify(exactly = 1) { musicDao.getAll() }
    }

    @Test
    fun `getByArtistId returns mapped result when found`() = runTest {
        // Given
        val artistId = 1
        val resultEntity = ResultEntity(
            artistId = artistId,
            artistName = "Test Artist",
            trackName = "Test Track"
        )

        coEvery { musicDao.getByArtistId(artistId) } returns resultEntity

        // When
        val result = repository.getByArtistId(artistId)

        // Then
        assertNotNull(result)
        assertEquals(artistId, result?.artistId)
        assertEquals("Test Artist", result?.artistName)
        coVerify(exactly = 1) { musicDao.getByArtistId(artistId) }
    }

    @Test
    fun `getByArtistId returns null when not found`() = runTest {
        // Given
        val artistId = 999

        coEvery { musicDao.getByArtistId(artistId) } returns null

        // When
        val result = repository.getByArtistId(artistId)

        // Then
        assertNull(result)
        coVerify(exactly = 1) { musicDao.getByArtistId(artistId) }
    }

    @Test
    fun `insert converts domain models to entities and inserts to database`() = runTest {
        // Given
        val results = listOf(
            Result(
                artistId = 1,
                artistName = "Test Artist 1",
                trackName = "Test Track 1"
            ),
            Result(
                artistId = 2,
                artistName = "Test Artist 2",
                trackName = "Test Track 2"
            )
        )

        // When
        repository.insert(results)

        // Then
        coVerify(exactly = 1) {
            musicDao.insert(match { entities ->
                entities.size == 2 &&
                entities[0].artistId == 1 &&
                entities[0].artistName == "Test Artist 1" &&
                entities[1].artistId == 2 &&
                entities[1].artistName == "Test Artist 2"
            })
        }
    }

    @Test
    fun `insert with empty list calls dao with empty list`() = runTest {
        // Given
        val results = emptyList<Result>()

        // When
        repository.insert(results)

        // Then
        coVerify(exactly = 1) {
            musicDao.insert(match { it.isEmpty() })
        }
    }

    @Test
    fun `deleteAll calls dao deleteAll`() = runTest {
        // When
        repository.deleteAll()

        // Then
        coVerify(exactly = 1) { musicDao.deleteAll() }
    }
}