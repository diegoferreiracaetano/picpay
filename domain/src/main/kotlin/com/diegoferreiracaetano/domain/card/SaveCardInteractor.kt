package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.map

class SaveCardInteractor(
    private val cardRepository: CardRepository,
    private val router: Router
) : Interactor<Card, Pair<Long, Router>>() {

    override fun execute(parameters: Card) = cardRepository.save(parameters).map {
        it to router
    }
}
