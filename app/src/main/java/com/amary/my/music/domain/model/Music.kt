package com.amary.my.music.domain.model

data class Music(
    val resultCount: Int = 0,
    val results: List<Result> = emptyList()
)