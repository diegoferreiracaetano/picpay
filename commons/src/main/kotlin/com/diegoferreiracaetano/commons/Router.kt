package com.diegoferreiracaetano.commons

import android.content.Context
import android.net.Uri

class Router(private val context: Context) {

    inner class Contacts {
        fun next(id: Int): Uri = Uri.parse(context.getString(R.string.router_contacts_to_welcome_card, id))
    }

    inner class Receipt {
        fun next(id: Int): Uri = Uri.parse(context.getString(R.string.router_receipt_next, id))
    }

    inner class Card {
        fun welcome(id: Int): Uri = Uri.parse(context.getString(R.string.router_welcome_card_to_card, id))
        fun next(id: Int): Uri = Uri.parse(context.getString(R.string.router_card_to_payment, id))
    }
}
