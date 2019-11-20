package com.diegoferreiracaetano.data.remote.payment

import com.diegoferreiracaetano.data.remote.transaction.TransactionEntity
import com.google.gson.annotations.SerializedName

internal class ConfirmationPaymentEntity(
    @SerializedName("transaction") val transaction: TransactionEntity
)