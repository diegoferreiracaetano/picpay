package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class UserInteractor(
    private val userRepository: UserRepository
) : Interactor<Unit, List<User>> {

    override fun execute(request: Unit) = userRepository.users()
}