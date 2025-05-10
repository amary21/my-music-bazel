package com.amary.my.music.feature

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicFeature() {
    val viewModel: MusicViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    MusicScreen(
        state = state,
        event = { uiEvent ->
            when(uiEvent) {
                is MusicEvent.OnPrepare -> {
                    scope.launch { sheetState.show() }
                    viewModel.onEvent(uiEvent)
                }
                else -> viewModel.onEvent(uiEvent)
            }

        },
    )

    MusicBottomSheet(
        state = state,
        sheetState = sheetState,
        event = { uiEvent ->
            when(uiEvent) {
                is MusicEvent.OnBack -> {
                    scope.launch { sheetState.hide() }
                }
                else -> viewModel.onEvent(uiEvent)
            }

        },
    )
}