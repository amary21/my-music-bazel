package com.amary.my.music.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amary.my.music.data.exoplayer.MusicPlayer
import com.amary.my.music.data.exoplayer.PlayerListener
import com.amary.my.music.domain.model.Result
import com.amary.my.music.domain.usecase.CurrentMusicUseCase
import com.amary.my.music.domain.usecase.ListMusicUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val listMusicUseCase: ListMusicUseCase,
    private val currentMusicUseCase: CurrentMusicUseCase,
    private val musicPlayer: MusicPlayer
) : ViewModel() {

    private var positionJob: Job? = null
    private val _state = MutableStateFlow(DetailState())
    val state get() = _state.asStateFlow()

    init {
        setupPlayerListener()
    }

    private fun setupPlayerListener() {
        musicPlayer.setListener(object : PlayerListener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _state.update {
                    it.copy(isPlaying = isPlaying)
                }
            }

            override fun onPlaybackStateChanged(isReady: Boolean) {
                if (isReady) {
                    _state.update {
                        it.copy(duration = musicPlayer.duration)
                    }
                }
            }
        })
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnBack -> Unit
            is DetailEvent.OnArgument -> setArgument(event.artistId)
            is DetailEvent.OnSeekTo -> seekTo(event.position)
            is DetailEvent.OnPlayPause -> playPause()
            is DetailEvent.OnNext -> next()
            is DetailEvent.OnPrevious -> prev()
        }
    }

    private fun setArgument(artistId: Int) = viewModelScope.launch {
        val results = listMusicUseCase.invoke()
        val currentResult = currentMusicUseCase.invoke(artistId)
        currentResult ?: return@launch

        _state.update {
            it.copy(
                artistId = artistId,
                results = results,
                selectedSong = currentResult
            )
        }

        prepare(currentResult)
    }

    private fun prepare(result: Result) {
        musicPlayer.prepare(result.previewUrl)

        _state.update {
            it.copy(selectedSong = result)
        }

        startPositionUpdate()
    }

    private fun startPositionUpdate() {
        positionJob?.cancel()
        positionJob = viewModelScope.launch {
            while (true) {
                _state.update {
                    it.copy(position = musicPlayer.currentPosition)
                }
                delay(500)
            }
        }
    }

    private fun playPause() {
        if (musicPlayer.isPlaying) {
            musicPlayer.pause()
        } else {
            musicPlayer.play()
        }
    }

    private fun seekTo(position: Long) {
        musicPlayer.seekTo(position)
    }

    private fun next() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex != -1 && currentIndex < results.lastIndex) {
            val nextSong = results[currentIndex + 1]
            prepare(nextSong)
        }
    }

    private fun prev() {
        val current = _state.value.selectedSong
        val results = _state.value.results
        val currentIndex = results.indexOfFirst { it.trackId == current?.trackId }

        if (currentIndex > 0) {
            val prevSong = results[currentIndex - 1]
            prepare(prevSong)
        }
    }

    override fun onCleared() {
        musicPlayer.release()
        positionJob?.cancel()
        super.onCleared()
    }
}