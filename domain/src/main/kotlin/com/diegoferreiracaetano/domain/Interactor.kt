package com.diegoferreiracaetano.domain

interface Interactor<R, T> {
    suspend fun execute(request: R): T
}
