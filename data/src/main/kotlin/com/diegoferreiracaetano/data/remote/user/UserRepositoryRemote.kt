package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import retrofit2.Retrofit

internal class UserRepositoryRemote(retrofit: Retrofit) {

    private val api = retrofit.create(PicpayApi::class.java)

    suspend fun getList() = api.getUsers()
}