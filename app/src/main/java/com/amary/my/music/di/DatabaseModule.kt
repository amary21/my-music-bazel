package com.amary.my.music.di

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amary.my.music.data.local.dao.MusicDao
import com.amary.my.music.data.local.entity.ResultEntity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Database(
    entities = [
        ResultEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase: RoomDatabase() {
    abstract fun musicDao(): MusicDao
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            MusicDatabase::class.java,
            "music.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single<MusicDao> { get<MusicDatabase>().musicDao() }
}