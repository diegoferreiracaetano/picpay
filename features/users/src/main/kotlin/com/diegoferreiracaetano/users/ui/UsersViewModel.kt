package com.diegoferreiracaetano.users.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.diegoferreiracaetano.domain.receipt.ReceiptInteractor
import com.diegoferreiracaetano.domain.user.SyncUserInteractor
import com.diegoferreiracaetano.domain.user.UserInteractor

internal class UsersViewModel(
    private val userInteractor: UserInteractor,
    private val receiptInteractor: ReceiptInteractor,
    private val syncUserInteractor: SyncUserInteractor
) : ViewModel() {

    fun job() = syncUserInteractor(Unit).asLiveData()

    private val _search = MutableLiveData<String>("")

    fun users() = Transformations.switchMap(_search) {
        userInteractor(it).asLiveData()
    }

    fun receipt(id: Long) = receiptInteractor(id).asLiveData()

    fun search(newText: String) = _search.postValue(newText)
}
