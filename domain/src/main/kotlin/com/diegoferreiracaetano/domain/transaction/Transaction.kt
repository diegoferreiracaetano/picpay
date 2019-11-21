package com.diegoferreiracaetano.domain.transaction

import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import java.util.*

data class Transaction (
    val id: Long,
    val date: Date,
    val value: Float,
    val success: Boolean,
    val status: StatusTransaction,
    val user: User,
    var card: Card? = null
)