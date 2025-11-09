package com.amary.my.music.di

import com.amary.my.music.coroutine.CoroutineQualifier
import com.amary.my.music.domain.repository.MusicRepository
import com.amary.my.music.data.remote.api.MusicApi
import com.amary.my.music.data.repository.MusicRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single<MusicApi>{
        get<Retrofit>().create(MusicApi::class.java)
    }
    single<MusicRepository> {
        MusicRepositoryImpl(
            musicApi = get(),
            musicDao = get(),
            ioDispatcher = get(named(CoroutineQualifier.IoDispatcher)),
        )
    }
}