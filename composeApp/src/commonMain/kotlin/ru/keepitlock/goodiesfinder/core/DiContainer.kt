package ru.keepitlock.goodiesfinder.core

import ru.keepitlock.goodiesfinder.data.repository.ProductRepositoryImpl
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository
import ru.keepitlock.goodiesfinder.domain.usecase.AddProductUseCase
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase

object DiContainer {
    // Data
    private val repository: ProductRepository = ProductRepositoryImpl()

    // Domain
    private val addProductUseCase = AddProductUseCase(repository)
    private val getProductsUseCase = GetProductsUseCase(repository)

    // Presentation
//    val addProductViewModel: AddProductViewModel = AddProductViewModel(addProductUseCase)
//    val productListViewModel: ProductListViewModel = ProductListViewModel(getProductsUseCase)
}
