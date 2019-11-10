package com.diegoferreiracaetano.receipt.di

import com.diegoferreiracaetano.receipt.ui.ReceiptViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val receiptModule: Module = module {
    viewModel { ReceiptViewModel(get()) }
}
