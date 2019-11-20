package com.diegoferreiracaetano.router.receipt

import com.diegoferreiracaetano.router.Router


class ReceiptRouter : Router {

    override fun navigate(any: Any) = "android-app://receipt/id/${any}"
}
