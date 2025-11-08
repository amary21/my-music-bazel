package com.amary.my.music.di

import com.amary.my.music.feature.MusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ MusicViewModel(get(), get()) }
}