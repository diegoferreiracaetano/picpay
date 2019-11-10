package com.diegoferreiracaetano.payment.ui

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.user.FindContactsByIdInteractor

internal class PaymentViewModel(private val interactor: FindContactsByIdInteractor) : ViewModel() {

    fun fetchContact(int: Int) = interactor.execute(int).asLiveData()

}