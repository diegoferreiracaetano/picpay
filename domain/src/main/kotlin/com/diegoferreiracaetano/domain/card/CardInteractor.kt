package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor

class CardInteractor(
    private val cardRepository: CardRepository
) : Interactor<Unit, Card?>() {

    override fun execute(parameters: Unit) = cardRepository.card()
}
