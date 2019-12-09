package com.diegoferreiracaetano.domain

import com.diegoferreiracaetano.router.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class Interactor<P, R> {


    operator fun invoke(parameters: P): Flow<Result<R>> {

        return flow {
            try {
                execute(parameters)
                    .flowOn(Dispatchers.IO)
                    .collect { emit(Result.success(it)) }
            } catch (t: Throwable) {
                emit(Result.failure(t))
            }
        }
    }

    protected abstract fun execute(parameters: P): Flow<R>
}
