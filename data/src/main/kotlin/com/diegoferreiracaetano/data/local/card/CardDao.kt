package com.diegoferreiracaetano.data.local.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CardDao {

    @Query("SELECT * FROM card ORDER BY id DESC LIMIT 1 ")
    fun card(): Flow<CardEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cardEntity: CardEntity): Long
}
