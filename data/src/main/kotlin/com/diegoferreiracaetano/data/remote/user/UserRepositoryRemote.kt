package com.diegoferreiracaetano.data.remote.user

import com.diegoferreiracaetano.data.remote.PicpayApi
import com.diegoferreiracaetano.data.remote.transform
import com.diegoferreiracaetano.domain.user.UserRepository
import retrofit2.Retrofit

internal class UserRepositoryRemote(private val api: PicpayApi) : UserRepository {

    override suspend fun users() = api.users().transform()
}