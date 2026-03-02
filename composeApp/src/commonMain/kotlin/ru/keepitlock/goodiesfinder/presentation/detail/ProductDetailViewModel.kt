package ru.keepitlock.goodiesfinder.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase

class ProductDetailViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)

    fun getProductById(productId: String): StateFlow<Product?> {
        viewModelScope.launch {
            val products = getProductsUseCase().first()
            _product.value = products.find { it.id == productId }
        }
        return _product.asStateFlow()
    }
}