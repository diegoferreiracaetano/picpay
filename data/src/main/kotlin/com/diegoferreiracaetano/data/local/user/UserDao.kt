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

    @Query("SELECT * FROM user WHERE nameQuery LIKE '%' || :string || '%' ORDER BY name")
    fun users(string: String): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: List<UserEntity>): List<Long>
}
