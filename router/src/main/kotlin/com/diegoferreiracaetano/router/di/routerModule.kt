package com.diegoferreiracaetano.router.di

import com.diegoferreiracaetano.router.Router
import com.diegoferreiracaetano.router.card.CardRouter
import com.diegoferreiracaetano.router.card.CardWelcomeRouter
import com.diegoferreiracaetano.router.payment.PaymentRouter
import com.diegoferreiracaetano.router.receipt.ReceiptRouter
import com.diegoferreiracaetano.router.user.UserRouter
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val routerModule: Module = module {

    single<Router>(named("card_welcome")) { CardWelcomeRouter() }

    single<Router>(named("card")) { CardRouter() }

    single<Router>(named("payment")) { PaymentRouter() }

    single<Router>(named("receipt")) { ReceiptRouter() }

    single<Router>(named("user")) { UserRouter() }

}
