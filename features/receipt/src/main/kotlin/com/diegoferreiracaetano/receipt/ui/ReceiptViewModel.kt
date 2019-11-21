package com.diegoferreiracaetano.receipt.ui

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.transaction.FindTransactionByIdInteractor

internal class ReceiptViewModel(private val interactor: FindTransactionByIdInteractor) : ViewModel() {

    fun transaction(id: Long) = interactor.execute(id).asLiveData()
}
