package com.diegoferreiracaetano.domain.di

import com.diegoferreiracaetano.domain.receipt.ReceiptInteractor
import com.diegoferreiracaetano.domain.user.ContactsInteractor
import com.diegoferreiracaetano.domain.user.FindContactsByIdInteractor
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {
    single { ContactsInteractor(get()) }

    single { FindContactsByIdInteractor(get()) }


    single { ReceiptInteractor() }
}
