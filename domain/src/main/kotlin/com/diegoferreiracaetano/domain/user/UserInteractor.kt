package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class UserInteractor(
    private val userRepository: UserRepository,
    private val cardRepository: CardRepository,
    private val cardRouter: Router,
    private val paymentRouter: Router
) : Interactor<String, Pair<List<User>, Router>> {

    override fun execute(request: String) = userRepository.users(request).flatMapMerge{ userId->
        cardRepository.card().map{
            if(it == null)
                userId to cardRouter
            else
                userId to  paymentRouter
        }
    }
}