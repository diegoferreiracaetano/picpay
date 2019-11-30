package com.diegoferreiracaetano.domain.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun users(string: String): Flow<List<User>>

    fun user(id: Long): Flow<User>

    fun save(user: List<User>): Flow<List<Long>>
}
