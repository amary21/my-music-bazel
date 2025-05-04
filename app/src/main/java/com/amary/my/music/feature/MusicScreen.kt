package com.amary.my.music.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amary.my.music.data.api.model.Result

@Composable
fun MusicScreen(
    state: MusicState,
    onSearch: (String) -> Unit,
    onPrepare: (Result) -> Unit,
    onSeekTo: (Long) -> Unit,
    onPlay: () -> Unit,
) {
    val search = remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TextField(
                value = search.value,
                onValueChange = { value ->
                    search.value = value
                    onSearch(value)
                },
                placeholder = { Text(text = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 32.dp,
                        horizontal = 16.dp
                    )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier.height(150.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {

                    }) {
                        Text(text = "Previous")
                    }
                    Button(onClick = {
                        onPlay()
                    }) {
                        Text(text = if (state.isPlaying) "Pause" else "Play")
                    }
                    Button(onClick = { }) {
                        Text(text = "Next")
                    }
                }

                Slider(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.position.toFloat(),
                    onValueChange = {
                        onSeekTo(it.toLong())
                    },
                    valueRange = 0f..(state.duration.toFloat().coerceAtLeast(1f))
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                AnimatedVisibility(visible = state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            item {
                AnimatedVisibility(visible = state.isError) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(text = state.message)
                    }
                }
            }
            itemsIndexed(
                items = state.results,
            ) { _, result ->
                Row(
                    modifier = Modifier
                        .height(150.dp)
                        .clickable {
                            onPrepare(result)
                            onPlay()
                        }
                ) {
//                    AsyncImage(
//                        modifier = Modifier
//                            .height(120.dp)
//                            .width(80.dp),
//                        model = result.artworkUrl100,
//                        contentDescription = ""
//                    )
                    Column {
                        Text(text = result.trackName)
                        Text(text = result.artistName)
                        Text(text = result.collectionName)
                    }
                }
            }
        }
    }
}