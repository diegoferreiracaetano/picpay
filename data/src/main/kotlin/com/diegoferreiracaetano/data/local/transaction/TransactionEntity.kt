package com.diegoferreiracaetano.data.local.transaction

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegoferreiracaetano.data.local.card.CardEntity
import com.diegoferreiracaetano.data.local.user.UserEntity

@Entity(tableName = "transaction")
internal class TransactionEntity(
    @PrimaryKey val id: Long,
    val date: Long,
    val value: Float,
    val success: Boolean,
    val status: String,
    @Embedded(prefix = "user_") val user: UserEntity,
    @Embedded(prefix = "card_") val card: CardEntity
)
