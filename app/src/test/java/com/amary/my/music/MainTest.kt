package com.amary.my.music

import com.amary.my.music.data.repository.MusicRepositoryImplTest
import com.amary.my.music.domain.usecase.CurrentMusicUseCaseTest
import com.amary.my.music.domain.usecase.ListMusicUseCaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MusicRepositoryImplTest::class,
    CurrentMusicUseCaseTest::class,
    ListMusicUseCaseTest::class,
)
class MainTest