package com.diegoferreiracaetano

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.work.Data
import androidx.work.WorkInfo
import com.diegoferreiracaetano.domain.ResultRouter
import com.diegoferreiracaetano.domain.card.Card
import com.diegoferreiracaetano.domain.user.User
import com.diegoferreiracaetano.router.Router
import java.util.UUID
import kotlinx.coroutines.flow.flow

internal object Mock {

    fun user() = User(
        0,
        "User",
        "@username"
    )

    fun users() = listOf(user())

    fun card() = Card(
        id = 1,
        brand = "MASTERCARD",
        number = 11111111111111,
        name = "DIEGO CAETANO",
        date = "11/11",
        cvv = 111
    )

    fun job() = WorkInfo(
        UUID.randomUUID(),
        WorkInfo.State.SUCCEEDED,
        Data.EMPTY,
        emptyList(),
        Data.EMPTY,
        0)
}

internal fun <T> T.toResultSuccessTest(router: Router) = flow {
    emit(Result.success(ResultRouter.add(this@toResultSuccessTest, router)))
}

internal fun <T> T.toLiveDataResultTest(): LiveData<Result<T>> {
    val result = Result.success(this)
    return liveData {
        emit(result)
    }
}

internal fun <T> T.toLiveDataTest(): LiveData<T> {
    return liveData {
        emit(this@toLiveDataTest)
    }
}

internal fun <T> T.toLiveDataResultTest(router: Router): LiveData<Result<ResultRouter<T>>> {
    val result = Result.success(ResultRouter.add(this, router))
    return liveData {
        emit(result)
    }
}
