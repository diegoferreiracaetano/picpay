package com.diegoferreiracaetano.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface UserDao {

    @Query("SELECT * FROM user WHERE id =:id")
    fun user(id: Long): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: UserEntity): Long

}