package com.diegoferreiracaetano.domain.order

import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User

data class Order(
    val user: User,
    val card: Card,
    var value: Float = 0f
)
