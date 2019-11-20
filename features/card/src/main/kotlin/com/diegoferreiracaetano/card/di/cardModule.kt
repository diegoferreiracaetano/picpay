package com.diegoferreiracaetano.card.di

import com.diegoferreiracaetano.card.ui.CardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

val cardModule: Module = module {
    viewModel { CardViewModel(get(), get(), get()) }
}