package com.diegoferreiracaetano.domain.card

import kotlinx.coroutines.flow.Flow

interface CardRepository {
    fun save(card: Card): Flow<Long>

    fun card(): Flow<Card?>
}
