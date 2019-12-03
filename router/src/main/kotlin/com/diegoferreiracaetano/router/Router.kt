package com.diegoferreiracaetano.router

interface Router {

    fun navigate(any: Any): String

    fun isStart(): Boolean = false
}
