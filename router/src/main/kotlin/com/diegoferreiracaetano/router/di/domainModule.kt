package com.diegoferreiracaetano.router.di

import com.diegoferreiracaetano.router.Router
import com.diegoferreiracaetano.router.contact.ContactRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val routerModule: Module = module {
    single<Router> { ContactRouter() }
}
