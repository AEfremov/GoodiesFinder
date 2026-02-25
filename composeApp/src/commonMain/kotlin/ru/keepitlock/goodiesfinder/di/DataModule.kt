package ru.keepitlock.goodiesfinder.di

import org.koin.dsl.module
import ru.keepitlock.goodiesfinder.data.repository.InMemoryProductRepository
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository
import ru.keepitlock.goodiesfinder.domain.usecase.AddProductUseCase
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase

val dataModule = module {
    single<ProductRepository> { InMemoryProductRepository() }

    factory { AddProductUseCase(get()) }
    factory { GetProductsUseCase(get()) }
}
