package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import kotlinx.coroutines.flow.flow

class SaveCardInteractor : Interactor<Card, Boolean> {

    override fun execute(request: Card) = flow {
        emit(
            true
        )
    }
}
