package com.diegoferreiracaetano.users.di

import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.diegoferreiracaetano.users.ui.UsersViewModel
import com.diegoferreiracaetano.users.work.SyncWorker
import com.diegoferreiracaetano.users.work.SyncWorker.Companion.TAG
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val usersModule: Module = module {
    viewModel { UsersViewModel(get(), get(), get()) }

    single {
        val workManager = WorkManager.getInstance(get())
        workManager.enqueue(get<OneTimeWorkRequest>())
        workManager
    }

    single {
        OneTimeWorkRequestBuilder<SyncWorker>()
            .addTag(TAG).build()
    }
}
