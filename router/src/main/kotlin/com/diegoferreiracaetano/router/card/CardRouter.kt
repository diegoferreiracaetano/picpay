package com.diegoferreiracaetano.router.card

import com.diegoferreiracaetano.router.Router

class CardRouter : Router {

    override fun navigate(any: Any) = "android-app://card/id/$any"
}
