package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.transaction.Transaction
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.map

class SavePaymentInteractor(
    private val paymentRepository: PaymentRepository,
    private val router: Router
) : Interactor<Payment, Pair<Transaction, Router>> {

    override fun execute(request: Payment) = paymentRepository.payment(request).map {
        it to router
    }
}
