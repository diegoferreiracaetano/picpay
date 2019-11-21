package com.diegoferreiracaetano.domain.transaction

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.domain.payment.Payment
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class FindTransactionByIdInteractor(
    private val repository: TransactionRepository
) : Interactor<Long, Transaction> {

    override fun execute(request: Long) = repository.transaction(request)
}
