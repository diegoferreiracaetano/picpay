package com.diegoferreiracaetano.data.remote

import com.diegoferreiracaetano.data.remote.payment.ConfirmationPaymentEntity
import com.diegoferreiracaetano.data.remote.payment.PaymentEntity
import com.diegoferreiracaetano.data.remote.user.UserEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface PicpayApi {

    @GET("tests/mobdev/users")
    fun users(): Flow<List<UserEntity>>

    @POST("tests/mobdev/transaction")
    fun sendPayment(@Body payment: PaymentEntity): Flow<ConfirmationPaymentEntity>
}
