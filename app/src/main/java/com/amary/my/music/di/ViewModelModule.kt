package com.amary.my.music.di

import com.amary.my.music.feature.detail.DetailViewModel
import com.amary.my.music.feature.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{ ListViewModel(get(), get()) }
    viewModel{ DetailViewModel(get(), get(), get()) }
}