package ru.keepitlock.goodiesfinder

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository

class TestProductRepository : ProductRepository {

    // Внутренний поток состояния, который мы можем менять из тестов
    private val _products = MutableStateFlow<List<Product>>(emptyList())

    // Публичный Flow только для чтения
    override fun getProducts(): Flow<List<Product>> = _products.asStateFlow()

    override suspend fun addProduct(product: Product) {
        // Для тестов GetProductByIdUseCase добавление не критично,
        // но реализуем для полноты контракта
        _products.value += product
    }

    /**
     * Helper-метод для тестов: позволяет установить нужный список продуктов
     * прямо перед вызовом UseCase.
     */
    fun setProducts(products: List<Product>) {
        _products.value = products
    }
}