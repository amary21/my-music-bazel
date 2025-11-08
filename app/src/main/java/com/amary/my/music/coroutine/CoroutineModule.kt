package com.amary.my.music.coroutine

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val coroutinesModule = module {
    single(named(CoroutineQualifier.IoDispatcher)) { Dispatchers.IO }
    single(named(CoroutineQualifier.MainDispatcher)) { Dispatchers.Main }
    single(named(CoroutineQualifier.DefaultDispatcher)) { Dispatchers.Default }
}