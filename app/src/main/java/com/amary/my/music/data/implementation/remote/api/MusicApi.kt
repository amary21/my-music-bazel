package com.amary.my.music.data.implementation.remote.api

import com.amary.my.music.data.implementation.remote.response.MusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {
    @GET("search")
    suspend fun searchMusics(
        @Query("term") term: String
    ): Response<MusicResponse>
}