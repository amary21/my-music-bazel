package com.amary.my.music.data.di

import com.amary.my.music.core.coroutine.CoroutineQualifier
import com.amary.my.music.data.api.repository.MusicRepository
import com.amary.my.music.data.implementation.remote.api.MusicApi
import com.amary.my.music.data.implementation.repository.MusicRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val musicDataModule = module {
    single<MusicApi>{ provideMusicApi(get()) }
    single<MusicRepository> {
        MusicRepositoryImpl(
            musicApi = get(),
            ioDispatcher = get(named(CoroutineQualifier.IoDispatcher)),
        )
    }
}

private fun provideMusicApi(retrofit: Retrofit): MusicApi {
    return retrofit.create(MusicApi::class.java)
}