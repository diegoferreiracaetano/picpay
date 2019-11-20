package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.transaction.Transaction
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun payment(payment: Payment): Flow<Transaction>
}
