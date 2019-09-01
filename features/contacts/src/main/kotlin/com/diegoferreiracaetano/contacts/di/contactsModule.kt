package com.diegoferreiracaetano.contacts.di

import com.diegoferreiracaetano.contacts.view.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val contactsViewModelModule: Module = module {
    viewModel { ContactsViewModel(get()) }
}
