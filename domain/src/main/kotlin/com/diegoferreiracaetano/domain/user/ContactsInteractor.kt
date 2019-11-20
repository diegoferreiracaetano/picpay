package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class ContactsInteractor(
    private val userRepository: UserRepository,
    private val cardRepository: CardRepository,
    private val cardRouter: Router,
    private val paymentRouter: Router
) : Interactor<Unit, Pair<List<User>, Router>> {

    override fun execute(request: Unit) = userRepository.users().flatMapMerge{ users->
        cardRepository.card().map{
            if(it == null)
                users to cardRouter
            else
                users to  paymentRouter
        }
    }
}