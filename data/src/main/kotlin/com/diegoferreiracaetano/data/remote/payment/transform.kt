package com.diegoferreiracaetano.data.remote.payment

import com.diegoferreiracaetano.domain.payment.Payment

internal fun Payment.transform() = PaymentEntity(
    cardNumber = card.number,
    cvv = card.cvv,
    value = value,
    expirySate = card.date,
    userId = user.id)