package com.diegoferreiracaetano.domain.card

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.CardRepository
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class WelcomdCardInteractor(
    private val router: Router
) : Interactor<Int, Pair<Int, Router>> {

    override fun execute(request: Int) = flow { emit(Pair(request, router)) }
}