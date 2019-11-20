package com.diegoferreiracaetano.data.local.card

import androidx.room.Entity

@Entity()
internal class CardEntity (
    val brand: String,
    val number: Long,
    val name: String,
    val date: String,
    val cvv: Int
)