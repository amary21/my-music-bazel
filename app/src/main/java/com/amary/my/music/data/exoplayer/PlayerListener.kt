package com.amary.my.music.data.exoplayer

interface PlayerListener {
    fun onIsPlayingChanged(isPlaying: Boolean)
    fun onPlaybackStateChanged(isReady: Boolean)
}