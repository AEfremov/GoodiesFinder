package ru.keepitlock.goodiesfinder.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.keepitlock.goodiesfinder.data.model.Product

interface ProductRepository {

    fun getProducts(): Flow<List<Product>>

    suspend fun addProduct(product: Product)
}
