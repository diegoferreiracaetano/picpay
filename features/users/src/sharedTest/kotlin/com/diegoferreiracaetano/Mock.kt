package com.diegoferreiracaetano

import com.diegoferreiracaetano.domain.user.User

internal object Mock {

    fun user() = User(
        0,
        "User",
        "@username"
    )

    fun users() = listOf(user())
}
