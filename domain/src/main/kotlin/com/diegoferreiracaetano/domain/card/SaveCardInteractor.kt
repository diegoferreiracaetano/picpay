package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import kotlinx.coroutines.flow.flow
import java.util.*

class SaveCardInteractor : Interactor<Card, Boolean> {

    override fun execute(request: Card) = flow {
        emit(
            true
        )
    }
}
