package com.diegoferreiracaetano.data.local.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
internal class CardEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val number: Long,
    val brand: String,
    val name: String,
    val date: String,
    val cvv: Int
)