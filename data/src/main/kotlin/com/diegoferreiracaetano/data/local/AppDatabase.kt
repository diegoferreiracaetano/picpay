package com.diegoferreiracaetano.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegoferreiracaetano.data.local.card.CardDao
import com.diegoferreiracaetano.data.local.card.CardEntity
import com.diegoferreiracaetano.data.local.transaction.TransactionDao
import com.diegoferreiracaetano.data.local.user.UserDao
import com.diegoferreiracaetano.data.local.user.UserEntity
import com.diegoferreiracaetano.data.local.transaction.TransactionEntity

@Database(entities = [CardEntity::class, TransactionEntity::class, UserEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
    abstract fun transactionDao(): TransactionDao
    abstract fun userDao(): UserDao
}
