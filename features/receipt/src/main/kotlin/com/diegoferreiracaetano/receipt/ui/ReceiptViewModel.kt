package com.diegoferreiracaetano.receipt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.diegoferreiracaetano.domain.transaction.FindTransactionByIdInteractor

internal class ReceiptViewModel(private val interactor: FindTransactionByIdInteractor) : ViewModel() {

    fun transaction(id: Long) = interactor(id).asLiveData()
}
