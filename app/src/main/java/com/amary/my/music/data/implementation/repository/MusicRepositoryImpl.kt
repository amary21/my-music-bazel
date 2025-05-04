package com.amary.my.music.data.implementation.repository

import com.amary.my.music.data.api.model.Result
import com.amary.my.music.data.api.repository.MusicRepository
import com.amary.my.music.data.implementation.remote.api.MusicApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MusicRepositoryImpl(
    private val musicApi: MusicApi,
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
}