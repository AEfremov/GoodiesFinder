package ru.keepitlock.goodiesfinder.domain.usecase

import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.repository.ProductRepository
import kotlin.time.Clock

class AddProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(name: String, latitude: Double, longitude: Double) {
        val product = Product(
            id = Clock.System.now().toString(),
            name = name,
            latitude = latitude,
            longitude = longitude
        )
        repository.addProduct(product)
    }
}
