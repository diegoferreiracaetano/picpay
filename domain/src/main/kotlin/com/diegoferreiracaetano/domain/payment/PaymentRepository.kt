package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.transaction.Transaction
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun sendPayment(payment: Payment): Flow<Transaction>
}
