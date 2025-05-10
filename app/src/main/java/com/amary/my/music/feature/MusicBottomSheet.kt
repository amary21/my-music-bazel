package com.amary.my.music.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.amary.my.music.ui.icon.Back
import com.amary.my.music.ui.icon.Icons
import com.amary.my.music.ui.icon.Next
import com.amary.my.music.ui.icon.Pause
import com.amary.my.music.ui.icon.Play
import com.amary.my.music.ui.icon.Prev
import com.amary.my.music.ui.util.toMinSec

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicBottomSheet(
    state: MusicState,
    sheetState: ModalBottomSheetState,
    event: (MusicEvent) -> Unit,
) {
    ModalBottomSheetLayout(sheetState = sheetState, sheetContent = {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color(0xFF0A091E),
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { event(MusicEvent.OnBack) }
                    ) {
                        Icon(
                            Icons.Back,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        text = state.selectedSong?.artistName.orEmpty(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                    )
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .height(319.dp)
                        .width(304.dp)
                        .clip(RoundedCornerShape(36.dp))
                        .background(Color.DarkGray),
                    model = state.selectedSong?.artworkUrl100,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    text = state.selectedSong?.trackName.orEmpty(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    text = state.selectedSong?.artistName.orEmpty(),
                    color = Color.White,
                    maxLines = 1,
                )
                Slider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                            start = 8.dp,
                            end = 8.dp,
                        ),
                    value = state.position.toFloat(),
                    onValueChange = { event(MusicEvent.OnSeekTo(it.toLong())) },
                    valueRange = 0f..(state.duration.toFloat()),
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF6156E2),
                        activeTrackColor = Color(0xFF6156E2),
                        inactiveTrackColor = Color.White
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        style = MaterialTheme.typography.body2,
                        text = state.position.toMinSec(),
                        color = Color.White,
                    )
                    Text(
                        style = MaterialTheme.typography.body2,
                        text = state.duration.toMinSec(),
                        color = Color.White,
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 32.dp,
                            start = 16.dp,
                            end = 16.dp,
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { event(MusicEvent.OnPrevious) }
                    ) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Prev,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Button(
                        onClick = { event(MusicEvent.OnPlayPause) },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF6156E2)
                        ),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.size(75.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = if (state.isPlaying)
                                Icons.Pause
                            else
                                Icons.Play,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = { event(MusicEvent.OnNext) }
                    ) {
                        Icon(
                            modifier = Modifier.size(40.dp),
                            imageVector = Icons.Next,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

            }
        }
    }) {}
}