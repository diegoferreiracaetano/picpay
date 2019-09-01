package com.diegoferreiracaetano.domain

interface Interactor<T> {
    suspend fun execute(): T
}
