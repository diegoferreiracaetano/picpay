package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class SaveUserInteractor(
    private val repositoryRemote: UserRepository,
    private val repositoryLocal: UserRepository
) : Interactor<Unit, List<Long>> {

    override fun execute(request: Unit) = repositoryRemote.users("").flatMapMerge {
        repositoryLocal.save(it)
    }
}