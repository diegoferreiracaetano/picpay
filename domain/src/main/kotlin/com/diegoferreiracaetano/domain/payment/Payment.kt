package com.diegoferreiracaetano.domain.payment

import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User

data class Payment(
    val user: User,
    val card: Card,
    var value: Float = 0f
)
