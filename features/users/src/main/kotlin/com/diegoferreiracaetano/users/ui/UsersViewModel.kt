package com.diegoferreiracaetano.users.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.diegoferreiracaetano.domain.receipt.ReceiptInteractor
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.domain.user.UserInteractor

internal class UsersViewModel(
    private val userInteractor: UserInteractor,
    private val receiptInteractor: ReceiptInteractor,
    workManager: WorkManager,
    workRequest: OneTimeWorkRequest
) : ViewModel() {

    val job: LiveData<WorkInfo>
    init {

        workManager.enqueue(workRequest)

        job = workManager.getWorkInfoByIdLiveData(workRequest.id)
    }

    private val _search = MutableLiveData<String>("")

    val users  = userInteractor("").asLiveData()


    fun receipt(id: Long) = receiptInteractor(id).asLiveData()

    fun search(newText: String) {
        _search.postValue(newText)
    }
}
