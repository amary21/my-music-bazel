package com.amary.my.music.di

import androidx.media3.exoplayer.ExoPlayer
import com.amary.my.music.data.exoplayer.MusicPlayer
import com.amary.my.music.data.exoplayer.MusicPlayerImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val exoPlayerModule = module {
    single {
        ExoPlayer.Builder(androidApplication()).build()
    }
    single<MusicPlayer> { MusicPlayerImpl(get()) }
}