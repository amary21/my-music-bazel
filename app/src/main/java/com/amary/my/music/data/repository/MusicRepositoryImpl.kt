package com.amary.my.music.data.repository

import com.amary.my.music.data.local.dao.MusicDao
import com.amary.my.music.data.local.entity.ResultEntity
import com.amary.my.music.domain.model.Result
import com.amary.my.music.domain.repository.MusicRepository
import com.amary.my.music.data.remote.api.MusicApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MusicRepositoryImpl(
    private val musicApi: MusicApi,
    private val musicDao: MusicDao,
    private val ioDispatcher: CoroutineDispatcher,
): MusicRepository {
    override suspend fun searchMusic(query: String): List<Result> {
        return withContext(ioDispatcher) {
            val response = musicApi.searchMusics(query)
            if (response.isSuccessful) {
                response.body()?.results?.map { it.map() } ?: emptyList()
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getAll(): List<Result> = withContext(ioDispatcher) {
        musicDao.getAll().map { it.map() }
    }

    override suspend fun getByArtistId(artistId: Int): Result? = withContext(ioDispatcher) {
        musicDao.getByArtistId(artistId)?.map()
    }

    override suspend fun insert(results: List<Result>) = withContext(ioDispatcher) {
        val resultEntities = results.map { ResultEntity.from(it) }
        musicDao.insert(resultEntities)
    }

    override suspend fun deleteAll() = withContext(ioDispatcher) {
        musicDao.deleteAll()
    }
}