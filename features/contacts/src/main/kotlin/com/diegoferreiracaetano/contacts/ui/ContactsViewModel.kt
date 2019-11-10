package com.diegoferreiracaetano.contacts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.asLiveData
import com.diegoferreiracaetano.domain.user.ContactsInteractor

internal class ContactsViewModel(private val contactsInteractor: ContactsInteractor) : ViewModel() {

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    fun fetchContacts() = contactsInteractor.execute(Unit).asLiveData()

    fun search(newText: String) {
        _search.postValue(newText)
    }
}
