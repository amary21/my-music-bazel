package com.amary.my.music.data.exoplayer

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class MusicPlayerImpl(
    private val exoPlayer: ExoPlayer
): MusicPlayer {

    private var listener: PlayerListener? = null

    override val isPlaying: Boolean
        get() = exoPlayer.isPlaying

    override val currentPosition: Long
        get() = exoPlayer.currentPosition

    override val duration: Long
        get() = exoPlayer.duration

    override fun prepare(url: String) {
        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.prepare()
    }

    override fun play() {
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun setListener(listener: PlayerListener) {
        this.listener = listener

        exoPlayer.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                listener.onIsPlayingChanged(isPlaying)
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                listener.onPlaybackStateChanged(playbackState == Player.STATE_READY)
            }
        })
    }
}