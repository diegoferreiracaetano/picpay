package com.diegoferreiracaetano.data.remote.transaction

import com.diegoferreiracaetano.data.remote.payment.ConfirmationPaymentEntity
import com.diegoferreiracaetano.data.remote.user.transform
import com.diegoferreiracaetano.domain.transaction.StatusTransaction.APPROVED
import com.diegoferreiracaetano.domain.transaction.StatusTransaction.FAILED
import com.diegoferreiracaetano.domain.transaction.Transaction
import com.diegoferreiracaetano.domain.user.User

private const val STATUS_OK = "Aprovada"

internal fun ConfirmationPaymentEntity.transform() = Transaction(
    id = transaction.id,
    timestamp = transaction.timestamp,
    value = transaction.value,
    success = transaction.success,
    status = if(transaction.status == STATUS_OK) APPROVED else FAILED,
    user = transaction.user.transform()
)