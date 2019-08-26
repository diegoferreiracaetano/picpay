package com.diegoferreiracaetano.domain.user

interface UserRepository {
    suspend fun users(): List<User>
}