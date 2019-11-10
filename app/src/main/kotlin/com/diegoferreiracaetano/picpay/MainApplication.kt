package com.diegoferreiracaetano.picpay

import android.app.Application
import com.diegoferreiracaetano.card.di.cardModule
import com.diegoferreiracaetano.contacts.di.contactsModule
import com.diegoferreiracaetano.data.di.dataModule
import com.diegoferreiracaetano.domain.di.domainModule
import com.diegoferreiracaetano.payment.di.paymentModule
import com.diegoferreiracaetano.receipt.di.receiptModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            loadKoinModules(listOf(
                contactsModule,
                domainModule,
                dataModule,
                paymentModule,
                receiptModule,
                cardModule
            ))
        }
    }
}
