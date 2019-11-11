package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import kotlinx.coroutines.flow.map

class FindContactsByIdInteractor(
    private val userRepository: UserRepository
) : Interactor<Int, User> {

    override fun execute(request: Int) = userRepository.users().map { user ->
        user.first { it.id == request }
    }
}
