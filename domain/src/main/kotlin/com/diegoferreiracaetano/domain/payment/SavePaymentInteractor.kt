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
    private val user: Router,
    private val receipt: Router
) : Interactor<Payment, Pair<Any, Router?>> {

    override fun execute(request: Payment) = paymentRepository.payment(request).flatMapMerge { transaction ->
        transaction.card = request.card
        transactionRepository.save(transaction).map {
            if (transaction.success && transaction.status == APPROVED)
                receipt.navigate(transaction.id) to user
            else
                transaction.id to null
        }
    }
}
