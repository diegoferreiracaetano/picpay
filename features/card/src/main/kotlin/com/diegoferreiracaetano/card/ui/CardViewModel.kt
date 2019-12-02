package com.diegoferreiracaetano.card.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.card.CardInteractor
import com.diegoferreiracaetano.domain.card.SaveCardInteractor
import com.diegoferreiracaetano.domain.card.WelcomdCardInteractor

internal class CardViewModel(
    private val cardInteractor: CardInteractor,
    private val welcomeCardInteractor: WelcomdCardInteractor,
    private val saveCardInteractor: SaveCardInteractor
) : ViewModel() {

    fun welcomeCard(userId: Long) = welcomeCardInteractor(userId).asLiveData()

    fun card() = cardInteractor(Unit).asLiveData()

    fun saveCard(card: Card) = saveCardInteractor(card).asLiveData()
}
