package com.amary.my.music.data.implementation.remote.response


import com.amary.my.music.data.api.model.Music
import com.google.gson.annotations.SerializedName

data class MusicResponse(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val results: List<ResultResponse>? = null
) {
    fun map(): Music {
        return Music(
            resultCount = resultCount ?: 0,
            results = results?.map { it.map() } ?: emptyList()
        )
    }
}