package com.diegoferreiracaetano.domain.user

import com.diegoferreiracaetano.domain.Interactor
import kotlinx.coroutines.flow.flatMapMerge

class SaveUserInteractor(
    private val repositoryRemote: UserRepository,
    private val repositoryLocal: UserRepository
) : Interactor<Unit, List<Long>> {

    override fun execute(request: Unit) = repositoryRemote.users("").flatMapMerge {
        repositoryLocal.save(it)
    }
}
