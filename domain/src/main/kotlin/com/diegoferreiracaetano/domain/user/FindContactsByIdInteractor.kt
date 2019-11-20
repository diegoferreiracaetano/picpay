package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.domain.order.Order
import com.diegoferreiracaetano.domain.payment.Payment
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class FindContactsByIdInteractor(
    private val userRepository: UserRepository,
    private val cardRepository: CardRepository
) : Interactor<Int, Order?> {

    override fun execute(request: Int) = userRepository.users().flatMapMerge { user ->
        cardRepository.card().map { card ->
            card?.let { Order(user.first { it.id == request }, it) }
        }
    }
}
