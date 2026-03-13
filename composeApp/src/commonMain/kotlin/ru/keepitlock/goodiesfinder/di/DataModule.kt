package ru.keepitlock.goodiesfinder.di

import org.koin.dsl.module
import ru.keepitlock.goodiesfinder.data.repository.ProductRepositoryImpl
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository
import ru.keepitlock.goodiesfinder.domain.usecase.AddProductUseCase
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductByIdUseCase
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase

val dataModule = module {

    single<ProductRepository> {
        val repo = ProductRepositoryImpl()
        repo.enableMockData()
        repo
    }

    factory { AddProductUseCase(get()) }
    factory { GetProductsUseCase(get()) }
    factory { GetProductByIdUseCase(get()) }
}
