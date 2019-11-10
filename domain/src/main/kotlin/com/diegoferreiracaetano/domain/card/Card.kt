package com.diegoferreiracaetano.domain.card

import java.util.Date

data class Card (
    val number: Long,
    val name: String,
    val date: Date,
    val cvv: Int
)