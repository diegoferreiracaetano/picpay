package com.diegoferreiracaetano.data.local.card

import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.card.CardRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class CardRepositoryLocal(private val dao: CardDao): CardRepository {

    override fun save(card: Card) = flow {
        emit(dao.insert(card.transform()))
    }

    override fun card() = dao.card().map { it?.transform() }
}
