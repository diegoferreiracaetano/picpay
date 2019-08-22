package com.diegoferreiracaetano.contacts.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.contacts.ResultData
import com.diegoferreiracaetano.contacts.execute
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.User

class ContactsViewModel(private val contactsInteractor: ContactsInteractor): ViewModel() {

    private val _contacts = MutableLiveData<ResultData<List<User>>>()
    val contacts : LiveData<ResultData<List<User>>> = _contacts

    fun fetchContacts() = execute(_contacts, contactsInteractor)

}