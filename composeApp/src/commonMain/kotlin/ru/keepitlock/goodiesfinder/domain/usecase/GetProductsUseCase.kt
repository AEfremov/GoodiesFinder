package ru.keepitlock.goodiesfinder.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
    }
}
