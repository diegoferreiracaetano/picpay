package com.diegoferreiracaetano.payment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.execute
import com.diegoferreiracaetano.domain.user.FindContactsByIdInteractor
import com.diegoferreiracaetano.domain.user.User

internal class PaymentViewModel(private val interactor: FindContactsByIdInteractor) : ViewModel() {

    private val _contact = MediatorLiveData<Result<User>>()
    val contact: LiveData<Result<User>> = _contact

    fun fetchContact(int: Int) {
        execute(_contact, int, interactor)
    }
}
