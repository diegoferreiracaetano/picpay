package com.diegoferreiracaetano.domain.receipt

import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import java.util.*

data class Receipt (
    val user: User,
    val card: Card,
    val date: Date,
    val transaction: Int,
    val value: Float,
    val total: Float
)