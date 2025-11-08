package com.amary.my.music

import android.app.Application
import com.amary.my.music.coroutine.coroutinesModule
import com.amary.my.music.di.exoPlayerModule
import com.amary.my.music.di.networkModule
import com.amary.my.music.di.dataModule
import com.amary.my.music.di.domainModule
import com.amary.my.music.di.viewModelModule
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
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}