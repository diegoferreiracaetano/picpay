package com.diegoferreiracaetano.contacts.di

import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.diegoferreiracaetano.contacts.ui.ContactsViewModel
import com.diegoferreiracaetano.contacts.work.SyncWorker
import com.diegoferreiracaetano.contacts.work.SyncWorker.Companion.TAG
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val contactsModule: Module = module {
    viewModel { ContactsViewModel(get(), get(), get()) }

    single { WorkManager.getInstance(get()) }

    single {
        OneTimeWorkRequestBuilder<SyncWorker>()
            .addTag(TAG).build()
    }
}
