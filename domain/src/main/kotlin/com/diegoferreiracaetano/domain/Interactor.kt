package com.diegoferreiracaetano.domain

import kotlinx.coroutines.flow.Flow

interface Interactor<R, T> {
    fun execute(request: R): Flow<T>
}
