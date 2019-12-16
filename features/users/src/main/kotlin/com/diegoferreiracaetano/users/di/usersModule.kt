package com.diegoferreiracaetano.users.di

import com.diegoferreiracaetano.users.ui.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val usersModule: Module = module {
    viewModel { UsersViewModel(get(), get(), get()) }
}
