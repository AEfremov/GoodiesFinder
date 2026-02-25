package ru.keepitlock.goodiesfinder.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.keepitlock.goodiesfinder.presentation.add.AddProductViewModel
import ru.keepitlock.goodiesfinder.presentation.list.ProductListViewModel

val viewModelModule = module {
    viewModel { AddProductViewModel(get()) }
    viewModel { ProductListViewModel(get()) }
}