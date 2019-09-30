package com.diegoferreiracaetano.contacts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.commons.execute
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.User

class ContactsViewModel(private val contactsInteractor: ContactsInteractor) : ViewModel() {

    private val _contacts = MediatorLiveData<Result<List<User>>>()
    val contacts: LiveData<Result<List<User>>> = _contacts

    private val _search = MutableLiveData<String>()
    val search: LiveData<String> = _search

    fun fetchContacts() {
        execute(_contacts, contactsInteractor)
    }

    fun search(newText: String) {
        _search.postValue(newText)
    }
}
