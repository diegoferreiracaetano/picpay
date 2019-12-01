package com.diegoferreiracaetano.data.local.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TransactionDao {

    @Query("SELECT * FROM `transaction` WHERE id =:id")
    fun transaction(id: Long): Flow<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactionEntity: TransactionEntity): Long
}
