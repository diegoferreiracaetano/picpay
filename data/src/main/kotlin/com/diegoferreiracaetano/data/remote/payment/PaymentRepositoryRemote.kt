package com.diegoferreiracaetano.data.remote.payment

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.domain.order.Order
import com.diegoferreiracaetano.domain.payment.Payment
import com.diegoferreiracaetano.domain.payment.PaymentRepository
import com.diegoferreiracaetano.domain.user.User
import kotlinx.coroutines.flow.Flow

internal class PaymentRepositoryRemote(private val api: PicpayApi) : PaymentRepository {

    override fun payment(order: Order): Flow<Boolean> = api.sendPayment(order.transform())
}
