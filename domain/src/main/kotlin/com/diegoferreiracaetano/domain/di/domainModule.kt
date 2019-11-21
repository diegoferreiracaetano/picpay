package com.diegoferreiracaetano.domain.di

import com.diegoferreiracaetano.domain.card.CardInteractor
import com.diegoferreiracaetano.domain.card.SaveCardInteractor
import com.diegoferreiracaetano.domain.card.WelcomdCardInteractor
import com.diegoferreiracaetano.domain.payment.SavePaymentInteractor
import com.diegoferreiracaetano.domain.transaction.FindTransactionByIdInteractor
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.FindContactsByIdInteractor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule: Module = module {
    single { ContactsInteractor(get(), get(), get(named("card_welcome")), get(named("payment"))) }

    single { WelcomdCardInteractor(get(named("card"))) }

    single { FindContactsByIdInteractor(get(), get()) }

    single { FindTransactionByIdInteractor(get()) }

    single { CardInteractor(get()) }

    single { SaveCardInteractor(get(), get(named("payment"))) }

    single { SavePaymentInteractor(get(), get(), get(named("receipt"))) }
}
