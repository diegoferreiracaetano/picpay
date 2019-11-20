package com.diegoferreiracaetano.data.remote.payment

import com.google.gson.annotations.SerializedName

internal class PaymentEntity(
    @SerializedName("card_number") val cardNumber: Long,
    @SerializedName("cvv") val cvv: Int,
    @SerializedName("value") val value: Float,
    @SerializedName("expiry_date") val expirySate: String,
    @SerializedName("destination_user_id") val userId: Int
)