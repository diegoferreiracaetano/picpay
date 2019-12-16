package com.diegoferreiracaetano.domain.user

import kotlinx.coroutines.flow.Flow

interface SyncUser {
    operator fun invoke(): Flow<Boolean>
}
