package com.amary.my.music.domain.repository

import com.amary.my.music.domain.model.Result

interface MusicRepository {
    suspend fun searchMusic(query: String): List<Result>
}