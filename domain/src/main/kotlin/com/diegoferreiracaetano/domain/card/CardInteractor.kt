package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import kotlinx.coroutines.flow.flow

class CardInteractor(
    private val cardRepository: CardRepository
) : Interactor<Unit, Card?> {

    override fun execute(request: Unit) = cardRepository.card()
}
