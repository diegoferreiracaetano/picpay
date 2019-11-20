package com.diegoferreiracaetano.data.remote.payment

import com.diegoferreiracaetano.domain.order.Order

internal fun Order.transform() = PaymentEntity(
    cardNumber = card.number,
    cvv = card.cvv,
    value = value,
    expirySate = card.date,
    userId = user.id)