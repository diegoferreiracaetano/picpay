package com.diegoferreiracaetano.domain.user

interface UserRepository {

    suspend fun getUsers(): List<User>
}