package com.diegoferreiracaetano.users.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.diegoferreiracaetano.commons.SnackbarMessage
import com.diegoferreiracaetano.domain.receipt.ReceiptInteractor
import com.diegoferreiracaetano.domain.user.UserInteractor
import com.diegoferreiracaetano.users.R
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

internal class UsersViewModel(
    private val userInteractor: UserInteractor,
    private val receiptInteractor: ReceiptInteractor,
    workManager: WorkManager,
    workRequest: OneTimeWorkRequest
) : ViewModel() {

    private val _error = MutableLiveData<SnackbarMessage>()
    val error: LiveData<SnackbarMessage> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _job = MutableLiveData<WorkInfo>()
    val job: LiveData<WorkInfo> = _job

    init {

        _loading.postValue(true)

        workManager.enqueue(workRequest)

        workManager.getWorkInfoById(workRequest.id).get().let {
            when (it.state) {
                WorkInfo.State.RUNNING ->  _loading.postValue(true)
                WorkInfo.State.FAILED -> {
                    _loading.postValue(false)
                    _error.postValue(SnackbarMessage(R.string.error_default_title, true))
                }
                WorkInfo.State.SUCCEEDED->  _loading.postValue(false)
                else -> null
            }
        }
    }

    private val _search = MutableLiveData<String>("")

    val users  = userInteractor("").map {
         it.getOrElse(it.getOrNull() ,{
            _loading.postValue(false)
            _error.postValue(SnackbarMessage(R.string.error_default_title, true, it))
        })
    }.asLiveData()


    fun receipt(id: Long) = receiptInteractor(id).asLiveData()

    fun search(newText: String) {
        _search.postValue(newText)
    }
}
