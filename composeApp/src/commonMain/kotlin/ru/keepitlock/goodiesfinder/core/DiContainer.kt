package ru.keepitlock.goodiesfinder.core

import ru.keepitlock.goodiesfinder.data.repository.InMemoryProductRepository
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository
import ru.keepitlock.goodiesfinder.domain.usecase.AddProductUseCase
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase
import ru.keepitlock.goodiesfinder.presentation.add.AddProductViewModel
import ru.keepitlock.goodiesfinder.presentation.list.ProductListViewModel

object DiContainer {
    // Data
    private val repository: ProductRepository = InMemoryProductRepository()

    // Domain
    private val addProductUseCase = AddProductUseCase(repository)
    private val getProductsUseCase = GetProductsUseCase(repository)

    // Presentation
//    val addProductViewModel: AddProductViewModel = AddProductViewModel(addProductUseCase)
//    val productListViewModel: ProductListViewModel = ProductListViewModel(getProductsUseCase)
}
