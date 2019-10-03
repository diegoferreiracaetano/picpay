package com.diegoferreiracaetano.commons

import android.content.Context
import android.net.Uri

class Router(private val context: Context) {

    inner class Contacts {
        fun next(id: Int): Uri = Uri.parse(context.getString(R.string.router_contacts_next, id))
    }
}
