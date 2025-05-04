package com.amary.my.music.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amary.my.music.domain.api.MusicUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicViewModel(
    private val musicUseCase: MusicUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MusicState())
    val state get() = _state.asStateFlow()

    fun searchMusic(query: String)= viewModelScope.launch {
        try {
            _state.update {
                it.copy(
                    isLoading = true,
                    isError = false,
                    results = emptyList(),
                    message = ""
                )
            }


            val useCase = musicUseCase.invoke(query)
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = false,
                    results = useCase,
                    message = ""
                )
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = true,
                    results = emptyList(),
                    message = e.message.orEmpty()
                )
            }
        }
    }
}