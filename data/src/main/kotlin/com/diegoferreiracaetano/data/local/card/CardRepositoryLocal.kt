package com.diegoferreiracaetano.data.local.card

import android.content.Context
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.card.CardRepository
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.flow.flow

internal class CardRepositoryLocal(context: Context): CardRepository {

    init {
        Hawk.init(context).build()
    }

    override fun save(card: Card) = flow {
        emit(Hawk.put(CARD, card.transform()))
    }

    override fun card() = flow {
        emit(if(Hawk.contains(CARD)) Hawk.get<Card>(CARD)  else null)
    }

    companion object {
        private const val CARD = "CARD"
    }
}