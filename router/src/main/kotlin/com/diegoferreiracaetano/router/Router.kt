package com.diegoferreiracaetano.router

import android.view.View

interface Router {

    fun navigate(url: String): View.OnClickListener
}