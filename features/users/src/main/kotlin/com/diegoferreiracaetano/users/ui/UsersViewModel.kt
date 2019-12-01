package com.diegoferreiracaetano.users.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.user.UserInteractor

internal class UsersViewModel(
    private val userInteractor: UserInteractor,
    workManager: WorkManager,
    workRequest: OneTimeWorkRequest
) : ViewModel() {

    val job: LiveData<WorkInfo>
    init {

        workManager.enqueue(workRequest)

        job = workManager.getWorkInfoByIdLiveData(workRequest.id)
    }

    private val _search = MutableLiveData<String>("")
    val search: LiveData<String> = _search

    fun users() = Transformations.switchMap(_search) {
        userInteractor.execute(it).asLiveData()
    }

    fun search(newText: String) {
        _search.postValue(newText)
    }
}
