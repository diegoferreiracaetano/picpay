package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.domain.transaction.StatusTransaction
import com.diegoferreiracaetano.domain.transaction.StatusTransaction.*
import com.diegoferreiracaetano.domain.transaction.Transaction
import com.diegoferreiracaetano.domain.transaction.TransactionRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class SavePaymentInteractor(
    private val paymentRepository: PaymentRepository,
    private val transactionRepository: TransactionRepository,
    private val successRouter: Router
) : Interactor<Payment, Pair<Long, Router?>> {

    override fun execute(request: Payment) = paymentRepository.payment(request).flatMapMerge { transaction ->
        transaction.card = request.card
        transactionRepository.save(transaction).map {
            if(transaction.success && transaction.status == APPROVED)
                transaction.id to successRouter
            else
                transaction.id to null
        }
    }
}
