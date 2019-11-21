package com.diegoferreiracaetano.domain.transaction

import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun save(transaction: Transaction): Flow<Long>

    fun transaction(id: Long): Flow<Transaction>
}
