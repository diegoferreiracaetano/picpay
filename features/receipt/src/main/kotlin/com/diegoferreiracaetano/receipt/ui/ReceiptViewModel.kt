package com.diegoferreiracaetano.receipt.ui

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.receipt.ReceiptInteractor

internal class ReceiptViewModel(private val interactor: ReceiptInteractor) : ViewModel() {

    fun receipt() = interactor.execute(Unit).asLiveData()

}