package ru.keepitlock.goodiesfinder.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.keepitlock.goodiesfinder.data.model.Product
import ru.keepitlock.goodiesfinder.domain.usecase.GetProductsUseCase

class ProductListViewModel(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    val products: StateFlow<List<Product>> = getProductsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
