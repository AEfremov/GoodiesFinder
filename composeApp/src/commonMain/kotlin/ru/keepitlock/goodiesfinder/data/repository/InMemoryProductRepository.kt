package ru.keepitlock.goodiesfinder.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository

/**
 *
 */
class InMemoryProductRepository : ProductRepository {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    override fun getProducts(): Flow<List<Product>> = _products.asStateFlow()

    override suspend fun addProduct(product: Product) {
        _products.update { currentList -> currentList + product }
    }
}
