package com.diegoferreiracaetano

import com.diegoferreiracaetano.domain.user.User

internal object Mock {

    fun user() = User(
        0,
        "User",
        "@username",
        "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"
    )

    fun users() = listOf(user())
}
