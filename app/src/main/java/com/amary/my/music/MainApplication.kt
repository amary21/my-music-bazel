package com.amary.my.music

import android.app.Application
import com.amary.my.music.core.coroutine.coroutinesModule
import com.amary.my.music.core.exoplayer.exoPlayerModule
import com.amary.my.music.core.network.networkModule
import com.amary.my.music.data.di.musicDataModule
import com.amary.my.music.domain.di.musicDomainModule
import com.amary.my.music.feature.musicFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(
                coroutinesModule,
                networkModule,
                exoPlayerModule,
                musicDataModule,
                musicDomainModule,
                musicFeatureModule
            )
        }
    }
}