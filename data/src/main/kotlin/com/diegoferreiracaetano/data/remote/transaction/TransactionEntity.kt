package com.diegoferreiracaetano.data.remote.transaction

import com.diegoferreiracaetano.data.remote.user.UserEntity
import com.google.gson.annotations.SerializedName

internal data class TransactionEntity(
    @SerializedName("id") val id: Long,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("value") val value: Float,
    @SerializedName("success") val success: Boolean,
    @SerializedName("status") val status: String,
    @SerializedName("destination_user") val user: UserEntity
)
