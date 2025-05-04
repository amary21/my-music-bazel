package com.amary.my.music.data.api.repository

import com.amary.my.music.data.api.model.Result

interface MusicRepository {
    suspend fun searchMusic(query: String): List<Result>
}