package com.amary.my.music.data.remote.response


import com.amary.my.music.domain.model.Music
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MusicResponse(
    @SerialName("resultCount")
    val resultCount: Int? = null,
    @SerialName("results")
    val results: List<ResultResponse>? = null
) {
    fun map(): Music {
        return Music(
            resultCount = resultCount ?: 0,
            results = results?.map { it.map() } ?: emptyList()
        )
    }
}