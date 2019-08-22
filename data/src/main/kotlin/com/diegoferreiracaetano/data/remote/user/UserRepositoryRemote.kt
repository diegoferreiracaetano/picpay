package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.data.remote.transform
import com.diegoferreiracaetano.domain.user.UserRepository
import retrofit2.Retrofit

internal class UserRepositoryRemote(retrofit: Retrofit) : UserRepository {

    private val api = retrofit.create(PicpayApi::class.java)

    override suspend fun getUsers() = api.getUsers().transform()
}