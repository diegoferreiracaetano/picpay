package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import kotlinx.coroutines.flow.flow

class CardInteractor : Interactor<Int, Card> {

    override fun execute(request: Int) = flow {
        emit(
            Card(1111222233334444, "Diego", "12/12", 123)
        )
    }
}
