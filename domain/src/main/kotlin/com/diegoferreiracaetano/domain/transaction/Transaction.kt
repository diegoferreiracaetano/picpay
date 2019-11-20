package com.diegoferreiracaetano.domain.transaction

import com.diegoferreiracaetano.domain.user.User

data class Transaction (
    val id: Int,
    val timestamp: Int,
    val value: Float,
    val success: Boolean,
    val status: StatusTransaction,
    val user: User
)