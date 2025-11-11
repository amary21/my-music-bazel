package com.amary.my.music.data.exoplayer

interface MusicPlayer {
    val isPlaying: Boolean
    val currentPosition: Long
    val duration: Long
    fun prepare(url: String)
    fun play()
    fun pause()
    fun seekTo(position: Long)
    fun release()
    fun setListener(listener: PlayerListener)
}