package com.diegoferreiracaetano.contacts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.contacts.execute
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.User

class ContactsViewModel(private val contactsInteractor: ContactsInteractor) : ViewModel() {

    private val _contacts = MediatorLiveData<Result<List<User>>>()
    val contacts: LiveData<Result<List<User>>> = _contacts

    fun fetchContacts() {
        execute(_contacts, contactsInteractor)
    }
}
