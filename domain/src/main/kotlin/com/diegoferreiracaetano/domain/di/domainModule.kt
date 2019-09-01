package com.diegoferreiracaetano.domain.di

import com.diegoferreiracaetano.domain.user.ContactsInteractor
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {
    single { ContactsInteractor(get()) }
}
