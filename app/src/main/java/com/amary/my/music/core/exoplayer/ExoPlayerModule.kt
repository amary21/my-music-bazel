package com.amary.my.music.core.exoplayer

import androidx.media3.exoplayer.ExoPlayer
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val exoPlayerModule = module {
    single {
        ExoPlayer.Builder(androidApplication()).build()
    }
}