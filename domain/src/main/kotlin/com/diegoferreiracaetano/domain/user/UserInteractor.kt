package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.ResultRouter
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class UserInteractor(
    private val userRepository: UserRepository,
    private val cardRepository: CardRepository,
    private val cardRouter: Router,
    private val paymentRouter: Router
) : Interactor<String, ResultRouter<List<User>>>() {

    override fun execute(parameters: String) = userRepository.users(parameters).flatMapLatest { userId ->
        cardRepository.card().map {
            if (it == null)
                ResultRouter.add(userId, cardRouter)
            else
                ResultRouter.add(userId, paymentRouter)
        }
    }
}
