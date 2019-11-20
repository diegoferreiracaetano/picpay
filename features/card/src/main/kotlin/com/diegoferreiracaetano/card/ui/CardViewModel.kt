package com.diegoferreiracaetano.card.ui

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.card.CardInteractor
import com.diegoferreiracaetano.domain.card.SaveCardInteractor
import com.diegoferreiracaetano.domain.card.WelcomdCardInteractor

internal class CardViewModel(
    private val cardInteractor: CardInteractor,
    private val welcomeCardInteractor: WelcomdCardInteractor,
    private val saveCardInteractor: SaveCardInteractor
) : ViewModel() {

    fun welcomeCard(userId: Int) = welcomeCardInteractor.execute(userId).asLiveData()

    fun card() = cardInteractor.execute(Unit).asLiveData()

    fun saveCard(card: Card) = saveCardInteractor.execute(card).asLiveData()
}
