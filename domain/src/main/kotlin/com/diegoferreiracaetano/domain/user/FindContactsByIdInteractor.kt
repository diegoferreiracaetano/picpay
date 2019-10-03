package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor

class FindContactsByIdInteractor(
    private val userRepository: UserRepository
) : Interactor<Int, User> {

    override suspend fun execute(request: Int) =
        userRepository.users().first { user -> user.id == request }
}
