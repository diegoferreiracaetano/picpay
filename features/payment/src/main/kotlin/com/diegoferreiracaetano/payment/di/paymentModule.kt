package com.diegoferreiracaetano.payment.di

import com.diegoferreiracaetano.payment.ui.PaymentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val paymentModule: Module = module {
    viewModel { PaymentViewModel(get()) }
}
