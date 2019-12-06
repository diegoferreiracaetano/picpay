package com.diegoferreiracaetano.domain.card

data class Card(
    val id: Long,
    val brand: String,
    val number: Long,
    val name: String,
    val date: String,
    val cvv: Int
)
