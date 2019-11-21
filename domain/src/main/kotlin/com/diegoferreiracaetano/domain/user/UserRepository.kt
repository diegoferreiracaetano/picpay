package com.diegoferreiracaetano.domain.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun users(): Flow<List<User>>

    fun user(id: Long): Flow<User>

    fun save(user: User): Flow<Long>
}
