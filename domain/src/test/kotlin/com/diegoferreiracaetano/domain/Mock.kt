package com.diegoferreiracaetano.domain

import com.diegoferreiracaetano.domain.user.User

internal object Mock {

    fun user() = User(0, "User", "@username", "http://www.google.com")

    fun users() = listOf(user())
}
