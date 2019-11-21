package com.diegoferreiracaetano.data.local.transaction

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.diegoferreiracaetano.data.local.card.CardEntity
import com.diegoferreiracaetano.data.local.user.UserEntity
import com.diegoferreiracaetano.domain.transaction.StatusTransaction
import com.diegoferreiracaetano.domain.user.User

@Entity(tableName = "transaction")
internal class TransactionEntity (
    @PrimaryKey val id: Long,
    val date: Long,
    val value: Float,
    val success: Boolean,
    val status: String,
    @Embedded(prefix = "user_") val user: UserEntity,
    @Embedded(prefix = "card_") val card: CardEntity
)