package com.diegoferreiracaetano.domain.transaction

import com.diegoferreiracaetano.domain.Interactor

class FindTransactionByIdInteractor(
    private val repository: TransactionRepository
) : Interactor<Long, Transaction>() {

    override fun execute(parameters: Long) = repository.transaction(parameters)
}
