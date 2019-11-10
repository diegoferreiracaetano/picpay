package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import kotlinx.coroutines.flow.flow
import java.util.*

class CardInteractor : Interactor<Int, Card> {

    override fun execute(request: Int) = flow {
        emit(
            Card(1111222233334444, "Diego", Date(), 123)
        )
    }
}
