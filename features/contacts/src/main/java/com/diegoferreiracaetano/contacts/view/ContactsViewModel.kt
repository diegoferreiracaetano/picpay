package com.diegoferreiracaetano.contacts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.contacts.execute
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.User

class ContactsViewModel(private val contactsInteractor: ContactsInteractor) : ViewModel() {

    lateinit var contacts: LiveData<Result<List<User>>>

    fun fetchContacts() {
        contacts = execute(contactsInteractor)
    }
}