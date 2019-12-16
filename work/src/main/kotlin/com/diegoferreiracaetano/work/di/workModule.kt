package com.diegoferreiracaetano.work.di

import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.diegoferreiracaetano.domain.user.SyncUser
import com.diegoferreiracaetano.work.job.SyncUserImpl
import com.diegoferreiracaetano.work.job.SyncUserWorker
import com.diegoferreiracaetano.work.job.SyncUserWorker.Companion.TAG
import org.koin.core.module.Module
import org.koin.dsl.module

val workModule: Module = module {

    single {
        val workManager = WorkManager.getInstance(get())
        workManager.enqueue(get<OneTimeWorkRequest>())
        workManager
    }

    single {
        OneTimeWorkRequestBuilder<SyncUserWorker>()
            .addTag(TAG).build()
    }

    single<SyncUser> {
        SyncUserImpl(get())
    }
}
