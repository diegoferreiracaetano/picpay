package com.diegoferreiracaetano.picpay

import android.app.Application
import com.diegoferreiracaetano.card.di.cardModule
import com.diegoferreiracaetano.data.di.dataModule
import com.diegoferreiracaetano.domain.di.domainModule
import com.diegoferreiracaetano.payment.di.paymentModule
import com.diegoferreiracaetano.receipt.di.receiptModule
import com.diegoferreiracaetano.router.di.routerModule
import com.diegoferreiracaetano.users.di.usersModule
import com.diegoferreiracaetano.work.di.workModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            loadKoinModules(listOf(
                usersModule,
                domainModule,
                dataModule,
                paymentModule,
                receiptModule,
                cardModule,
                routerModule,
                workModule
            ))
        }
    }
}
