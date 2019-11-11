package com.diegoferreiracaetano.card.ui

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.card.CardInteractor
import com.diegoferreiracaetano.domain.card.SaveCardInteractor

internal class CardViewModel(
    private val cardInteractor: CardInteractor,
    private val saveCardInteractor: SaveCardInteractor
) : ViewModel() {

    fun card(userId: Int) = cardInteractor.execute(userId).asLiveData()

    fun saveCard(card: Card) = saveCardInteractor.execute(card).asLiveData()
}
