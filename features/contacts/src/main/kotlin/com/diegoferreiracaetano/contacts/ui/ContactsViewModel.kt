package com.diegoferreiracaetano.contacts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.user.SaveUserInteractor
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.domain.user.UserInteractor

internal class ContactsViewModel(
    private val userInteractor: UserInteractor,
    private val saveUserInteractor: SaveUserInteractor) : ViewModel() {

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    fun users() = userInteractor.execute(Unit).asLiveData()

    fun save(user: User) = saveUserInteractor.execute(user).asLiveData()

    fun search(newText: String) {
        _search.postValue(newText)
    }
}
