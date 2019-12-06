package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.domain.payment.Payment
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class FindContactsByIdInteractor(
    private val userRepository: UserRepository,
    private val cardRepository: CardRepository,
    private val router: Router
) : Interactor<Long, Pair<Payment, Router>?>() {

    override fun execute(parameters: Long) = userRepository.user(parameters).flatMapMerge { user ->
        cardRepository.card().map { card ->
            card?.let { Pair(Payment(user, it), router) }
        }
    }
}
