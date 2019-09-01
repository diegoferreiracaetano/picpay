package com.diegoferreiracaetano.data.remote

import com.diegoferreiracaetano.data.remote.user.UserEntity
import retrofit2.http.GET

internal interface PicpayApi {

    @GET("tests/mobdev/users")
    suspend fun users(): List<UserEntity>
}
