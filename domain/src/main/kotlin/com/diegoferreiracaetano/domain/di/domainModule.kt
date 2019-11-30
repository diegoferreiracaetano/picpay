package com.diegoferreiracaetano.domain.di

import com.diegoferreiracaetano.domain.card.CardInteractor
import com.diegoferreiracaetano.domain.card.SaveCardInteractor
import com.diegoferreiracaetano.domain.card.WelcomdCardInteractor
import com.diegoferreiracaetano.domain.payment.SavePaymentInteractor
import com.diegoferreiracaetano.domain.transaction.FindTransactionByIdInteractor
import com.diegoferreiracaetano.domain.user.UserInteractor
import com.diegoferreiracaetano.domain.user.FindContactsByIdInteractor
import com.diegoferreiracaetano.domain.user.SaveUserInteractor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule: Module = module {
    single { UserInteractor(get(named("local")), get(), get(named("card_welcome")), get(named("payment"))) }

    single { SaveUserInteractor(get(), get(named("local"))) }

    single { WelcomdCardInteractor(get(named("card"))) }

    single { FindContactsByIdInteractor(get(named("local")), get()) }

    single { FindTransactionByIdInteractor(get()) }

    single { CardInteractor(get()) }

    single { SaveCardInteractor(get(), get(named("payment"))) }

    single { SavePaymentInteractor(get(), get(), get(named("receipt"))) }
}
