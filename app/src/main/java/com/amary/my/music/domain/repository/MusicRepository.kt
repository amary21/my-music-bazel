package com.amary.my.music.domain.repository

import com.amary.my.music.domain.model.Result

interface MusicRepository {
    suspend fun searchMusic(query: String): List<Result>

    suspend fun getAll(): List<Result>

    suspend fun getByArtistId(artistId: Int): Result?

    suspend fun insert(results: List<Result>)

    suspend fun deleteAll()
}