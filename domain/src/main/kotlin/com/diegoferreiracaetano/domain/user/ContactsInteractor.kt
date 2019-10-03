package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor

class ContactsInteractor(
    private val userRepository: UserRepository
) : Interactor<Unit, List<User>> {

    override suspend fun execute(request: Unit) = userRepository.users()
}
