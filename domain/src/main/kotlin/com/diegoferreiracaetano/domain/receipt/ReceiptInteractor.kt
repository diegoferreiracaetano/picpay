package com.diegoferreiracaetano.domain.receipt

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.flow.flow

class ReceiptInteractor(
    private val router: Router
) : Interactor<Long, Pair<Long, Router>>() {

    override fun execute(parameters: Long) = flow { emit(Pair(parameters, router)) }
}
