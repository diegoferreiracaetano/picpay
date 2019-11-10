package com.diegoferreiracaetano.domain.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun users(): Flow<List<User>>
}
