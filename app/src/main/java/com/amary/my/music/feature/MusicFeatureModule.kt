package com.amary.my.music.feature

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val musicFeatureModule = module {
    viewModel{ MusicViewModel(get()) }
}