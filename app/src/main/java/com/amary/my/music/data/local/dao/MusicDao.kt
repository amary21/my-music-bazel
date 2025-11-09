package com.amary.my.music.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amary.my.music.data.local.entity.ResultEntity

@Dao
interface MusicDao {
    @Query("SELECT * FROM result")
    suspend fun getAll(): List<ResultEntity>

    @Query("SELECT * FROM result WHERE artistId = :artistId")
    suspend fun getByArtistId(artistId: Int): ResultEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(results: List<ResultEntity>)

    @Query("DELETE FROM result")
    suspend fun deleteAll()
}