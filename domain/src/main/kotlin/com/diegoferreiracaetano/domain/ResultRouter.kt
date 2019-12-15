package com.diegoferreiracaetano.domain

import com.diegoferreiracaetano.router.Router

data class ResultRouter<T>(
    val result: T,
    val router: Router
) {
    companion object {
        fun <T> add(value: T, router: Router): ResultRouter<T> = ResultRouter(value, router)
    }
}
