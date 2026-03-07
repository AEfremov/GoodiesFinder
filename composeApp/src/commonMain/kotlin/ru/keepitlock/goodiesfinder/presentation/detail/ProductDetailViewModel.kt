package ru.keepitlock.goodiesfinder.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductByIdUseCase
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase

class ProductDetailViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)

//    fun getProductById(productId: String): StateFlow<Product?> {
//        viewModelScope.launch {
//            val products = getProductsUseCase().first()
//            _product.value = products.find { it.id == productId }
//        }
//        return _product.asStateFlow()
//    }

    /**
     * Возвращает StateFlow с продуктом.
     * Используем stateIn, чтобы подписаться на Flow из UseCase и кэшировать состояние для UI.
     */
    fun getProductById(productId: String): StateFlow<Product?> {
        return getProductByIdUseCase(productId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
    }
}