package com.diegoferreiracaetano.router.payment

import com.diegoferreiracaetano.router.Router


class PaymentRouter : Router {

    override fun navigate(any: Any) = "android-app://payment/id/${any}"
}
