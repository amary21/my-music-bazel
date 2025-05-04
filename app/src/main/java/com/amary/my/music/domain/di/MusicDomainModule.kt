package com.amary.my.music.domain.di

import com.amary.my.music.core.coroutine.CoroutineQualifier
import com.amary.my.music.domain.api.MusicUseCase
import com.amary.my.music.domain.implementation.MusicUseCaseImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val musicDomainModule = module {
    single<MusicUseCase> {
        MusicUseCaseImpl(
            musicRepository = get(),
            dispatcher = get(named(CoroutineQualifier.IoDispatcher))
        )
    }
}