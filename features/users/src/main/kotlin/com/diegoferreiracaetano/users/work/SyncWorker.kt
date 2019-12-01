package com.diegoferreiracaetano.users.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.diegoferreiracaetano.domain.user.SaveUserInteractor
import kotlinx.coroutines.flow.collect
import org.koin.core.KoinComponent
import org.koin.core.inject

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val saveUserInteractor: SaveUserInteractor by inject()

    override suspend fun doWork(): Result {
        return try {
            saveUserInteractor.execute(Unit).collect()
            Result.success()
        }catch (t: Throwable) {
            Result.failure()
        }
    }

    companion object {
        const val TAG = "TAG"
    }
}