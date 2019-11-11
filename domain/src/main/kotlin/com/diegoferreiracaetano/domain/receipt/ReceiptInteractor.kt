package com.diegoferreiracaetano.domain.receipt

import com.diegoferreiracaetano.domain.Interactor
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import kotlinx.coroutines.flow.flow
import java.util.*

class ReceiptInteractor : Interactor<Unit, Receipt> {

    override fun execute(request: Unit) = flow {
        emit(Receipt(
            User(1, "Diego Caetano", "@diego", "https://randomuser.me/api/portraits/women/37.jpg"),
            Card(1111222233334444, "Diego", "12/12", 123),
            date = Date(),
            transaction = 23000,
            value = 19.00f,
            total = 19.00f
        ))
    }
}
