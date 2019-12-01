package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flow

class WelcomdCardInteractor(
    private val router: Router
) : Interactor<Long, Pair<Long, Router>> {

    override fun execute(request: Long) = flow { emit(Pair(request, router)) }
}
