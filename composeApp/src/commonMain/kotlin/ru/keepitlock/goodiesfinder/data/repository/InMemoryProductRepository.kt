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
class InMemoryProductRepository(
    private val useMockData: Boolean = false,
) : ProductRepository {

    private val mockProducts = listOf(
        Product(id = "1", name = "Молоко", latitude = 55.7558, longitude = 37.6173),
        Product(id = "2", name = "Хлеб", latitude = 55.7512, longitude = 37.6184),
        Product(id = "3", name = "Яйца", latitude = 55.7539, longitude = 37.6208),
        Product(id = "4", name = "Масло", latitude = 55.7520, longitude = 37.6150),
        Product(id = "5", name = "Сыр", latitude = 55.7545, longitude = 37.6190),
        Product(id = "6", name = "Колбаса", latitude = 55.7560, longitude = 37.6210),
        Product(id = "7", name = "Рыба", latitude = 55.7530, longitude = 37.6160),
        Product(id = "8", name = "Мясо", latitude = 55.7555, longitude = 37.6195),
        Product(id = "9", name = "Овощи", latitude = 55.7540, longitude = 37.6175),
        Product(id = "10", name = "Фрукты", latitude = 55.7525, longitude = 37.6165),
        Product(id = "11", name = "Крупы", latitude = 55.7550, longitude = 37.6180),
        Product(id = "12", name = "Макароны", latitude = 55.7535, longitude = 37.6155),
        Product(id = "13", name = "Сахар", latitude = 55.7565, longitude = 37.6200),
        Product(id = "14", name = "Соль", latitude = 55.7515, longitude = 37.6145),
        Product(id = "15", name = "Чай", latitude = 55.7570, longitude = 37.6215)
    )

    private val _products = MutableStateFlow<List<Product>>(
        if (useMockData) mockProducts else emptyList()
    )

    override fun getProducts(): Flow<List<Product>> = _products.asStateFlow()

    override suspend fun addProduct(product: Product) {
        _products.update { currentList ->
            if (useMockData) {
                mockProducts + product
            } else {
                currentList + product
            }
        }
    }

    fun enableMockData() {
        _products.value = mockProducts
    }

    fun clearData() {
        _products.value = emptyList()
    }
}
