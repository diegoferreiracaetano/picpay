package com.diegoferreiracaetano.router

import android.net.Uri
import android.view.View
import androidx.navigation.findNavController

abstract class BaseRouter {

    fun navigate(url: String) = View.OnClickListener {
        it.findNavController().navigate(Uri.parse(url))
    }
}
