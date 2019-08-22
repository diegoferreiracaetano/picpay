package com.diegoferreiracaetano.data.remote.user

import com.google.gson.annotations.SerializedName

internal class UserEntity (
    @SerializedName("id") val id:Int,
    @SerializedName("name") val name:String,
    @SerializedName("username") val username:String,
    @SerializedName("img") val img:String
)