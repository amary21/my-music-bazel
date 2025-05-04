package com.amary.my.music.data.api.model

data class Music(
    val resultCount: Int = 0,
    val results: List<Result> = emptyList()
)