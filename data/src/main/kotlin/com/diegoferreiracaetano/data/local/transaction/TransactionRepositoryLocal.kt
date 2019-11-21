package com.diegoferreiracaetano.data.local.transaction

import com.diegoferreiracaetano.domain.transaction.Transaction
import com.diegoferreiracaetano.domain.transaction.TransactionRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class TransactionRepositoryLocal(private val dao: TransactionDao): TransactionRepository {

    override fun save(transaction: Transaction) = flow {
        emit(dao.insert(transaction.transform()))
    }

    override fun transaction(id: Long) = dao.transaction(id).map { it.transform() }
}
