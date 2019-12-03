package com.diegoferreiracaetano.users.di

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.diegoferreiracaetano.users.ui.UsersViewModel
import com.diegoferreiracaetano.users.work.SyncWorker
import com.diegoferreiracaetano.users.work.SyncWorker.Companion.TAG
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val usersModule: Module = module {
    viewModel { UsersViewModel(get(), get(), get(), get()) }

    single { WorkManager.getInstance(get()) }

    single {
        OneTimeWorkRequestBuilder<SyncWorker>()
            .addTag(TAG).build()
    }
}
