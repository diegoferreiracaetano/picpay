package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.order.Order
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SavePaymentInteractor(
    private val paymentRepository: PaymentRepository,
    private val router: Router
) : Interactor<Order, Pair<Payment, Router>> {

    override fun execute(request: Order) = paymentRepository.payment(request).map {
        Payment(1, request.user, request.card, request.value) to router
    }
}
