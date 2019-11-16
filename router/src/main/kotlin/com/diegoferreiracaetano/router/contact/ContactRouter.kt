package com.diegoferreiracaetano.router.contact

import com.diegoferreiracaetano.router.BaseRouter
import com.diegoferreiracaetano.router.Router

class ContactRouter : BaseRouter(), Router {

    fun welcomeCard(id: Int) = navigate("android-app://card/welcome/$id")
}
