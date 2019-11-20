package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.order.Order
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun payment(order: Order): Flow<Boolean>
}
