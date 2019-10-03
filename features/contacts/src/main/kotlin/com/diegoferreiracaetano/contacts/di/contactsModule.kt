package com.diegoferreiracaetano.contacts.di

import com.diegoferreiracaetano.contacts.ui.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val contactsModule: Module = module {
    viewModel { ContactsViewModel(get()) }
}
