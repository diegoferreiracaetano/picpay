package com.diegoferreiracaetano.data.remote.payment

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.data.remote.transaction.transform
import com.diegoferreiracaetano.domain.payment.Payment
import com.diegoferreiracaetano.domain.payment.PaymentRepository
import kotlinx.coroutines.flow.map

internal class PaymentRepositoryRemote(private val api: PicpayApi) : PaymentRepository {

    override fun sendPayment(payment: Payment) = api
        .sendPayment(payment.transform())
        .map { it.transform() }
}
