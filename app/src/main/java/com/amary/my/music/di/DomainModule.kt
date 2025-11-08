package com.amary.my.music.di

import com.amary.my.music.coroutine.CoroutineQualifier
import com.amary.my.music.domain.usecase.MusicUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory {
        MusicUseCase(
            musicRepository = get(),
            dispatcher = get(named(CoroutineQualifier.IoDispatcher))
        )
    }
}