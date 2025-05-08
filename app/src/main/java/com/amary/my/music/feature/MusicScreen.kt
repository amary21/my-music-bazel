package com.amary.my.music.feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.amary.my.music.data.api.model.Result
import com.amary.my.music.ui.icon.Icons
import com.amary.my.music.ui.icon.Search

@Composable
fun MusicScreen(
    state: MusicState,
    onSearch: (String) -> Unit,
    onPrepare: (Result) -> Unit,
    onSeekTo: (Long) -> Unit,
    onPlay: () -> Unit,
) {
    val search = remember { mutableStateOf("Linkin Park") }

    LaunchedEffect(Unit) {
        onSearch(search.value)
    }

    Scaffold(
        backgroundColor = Color(0xFF0A091E),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "🎵 Amary Music",
                    style = MaterialTheme.typography.h5.copy(
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                var query by remember { mutableStateOf("") }

                TextField(
                    value = query,
                    onValueChange = {
                        query = it
                        onSearch(it)
                    },
                    placeholder = {
                        Text("Search Music", color = Color.Gray)
                    },
                    leadingIcon = {
                        Icon(
                            Icons.Search,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFF1D1B3A),
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        textColor = Color.White
                    ),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            AnimatedVisibility(visible = state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            AnimatedVisibility(visible = state.isError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.message)
                }
            }

            AnimatedVisibility(visible = !state.isError && !state.isLoading) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    itemsIndexed(
                        items = state.results,
                    ) { _, result ->
                        Row(
                            modifier = Modifier
                                .height(150.dp)
                                .clickable {
                                    if (!state.isPlaying) {
                                        onPrepare(result)
                                    }

                                    onPlay()
                                }
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.DarkGray),
                                model = result.artworkUrl100,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = result.trackName,
                                    color = if (state.selectedSong?.trackId == result.trackId)
                                        Color(0xFF6156E2)
                                    else
                                        Color.White,
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(fontSize = 17.sp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = result.artistName,
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${result.trackCount} / steams",
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MusicScreenPreview() {
    MusicScreen(
        state = MusicState(),
        onSearch = {},
        onPrepare = {},
        onSeekTo = {},
        onPlay = {}
    )
}