package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.transaction.StatusTransaction.APPROVED
import com.diegoferreiracaetano.domain.transaction.TransactionRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class SavePaymentInteractor(
    private val paymentRepository: PaymentRepository,
    private val transactionRepository: TransactionRepository,
    private val router: Router
) : Interactor<Payment, Pair<Long, Router?>>() {

    override fun execute(parameters: Payment) = paymentRepository.sendPayment(parameters).flatMapMerge { transaction ->
        transaction.card = parameters.card
        transactionRepository.save(transaction).map {
            if (transaction.success && transaction.status == APPROVED)
                transaction.id to router
            else
                transaction.id to null
        }
    }
}
