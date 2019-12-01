package com.diegoferreiracaetano.router.card

import com.diegoferreiracaetano.router.Router

class CardWelcomeRouter : Router {

    override fun navigate(any: Any) = "android-app://card/welcome/$any"
}
