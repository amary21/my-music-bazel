package com.amary.my.music.di

import com.amary.my.music.coroutine.CoroutineQualifier
import com.amary.my.music.domain.usecase.CurrentMusicUseCase
import com.amary.my.music.domain.usecase.ListMusicUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory {
        ListMusicUseCase(
            musicRepository = get(),
            dispatcher = get(named(CoroutineQualifier.IoDispatcher))
        )
    }
    factory {
        CurrentMusicUseCase(
            musicRepository = get(),
            dispatcher = get(named(CoroutineQualifier.IoDispatcher))
        )
    }
}