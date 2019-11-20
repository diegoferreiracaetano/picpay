package com.diegoferreiracaetano.domain.receipt

import com.diegoferreiracaetano.domain.Interactor
import kotlinx.coroutines.flow.flow

class ReceiptInteractor : Interactor<Unit, Boolean> {

    override fun execute(request: Unit) = flow {
        emit(true)
    }
}
