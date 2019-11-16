package com.diegoferreiracaetano.domain

import kotlinx.coroutines.flow.Flow

interface Interactor<P, T> {
    fun execute(request: P): Flow<T>
}
