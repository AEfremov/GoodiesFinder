package ru.keepitlock.goodiesfinder.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository

/**
 * Usecase для получения продукта по id
 */
class GetProductByIdUseCase(
    private val repository: ProductRepository
) {

    /**
     * Возвращает Flow, который эмитит найденный продукт или null, если продукт не найден.
     */
    operator fun invoke(productId: String): Flow<Product?> {
        return repository.getProducts().map { products ->
            products.find { it.id == productId }
        }
    }
}