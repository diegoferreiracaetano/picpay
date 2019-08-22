package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor

class ContactsInteractor(
   private val userRepository: UserRepository
): Interactor<List<User>> {

   override suspend fun execute() = userRepository.getUsers()
}