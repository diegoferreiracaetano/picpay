package com.diegoferreiracaetano.work.job

import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.await
import com.diegoferreiracaetano.domain.user.SyncUser
import com.diegoferreiracaetano.work.job.SyncUserWorker.Companion.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class SyncUserImpl(
    private val workManager: WorkManager
) : SyncUser {

    override fun invoke() = callbackFlow {

        workManager.getWorkInfosByTag(TAG).let {
            try {
                it.await().forEach {
                    when (it.state) {
                        WorkInfo.State.SUCCEEDED -> offer(true)
                        WorkInfo.State.FAILED -> close(Throwable("fail job"))
                    }
                }
            } catch (e: Throwable) {
                close(e)
            }
        }

        awaitClose { cancel() }
    }
}
