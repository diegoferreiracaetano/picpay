package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor

class SyncUserInteractor(
    private val syncUser: SyncUser
) : Interactor<Unit, Boolean>() {

    override fun execute(parameters: Unit) = syncUser()
}
