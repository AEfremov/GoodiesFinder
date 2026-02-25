package ru.keepitlock.goodiesfinder.presentation.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.keepitlock.goodiesfinder.domain.usecase.AddProductUseCase

class AddProductViewModel(
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    fun saveProduct(name: String, lat: String, lon: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val latitude = lat.toDoubleOrNull() ?: 0.0
            val longitude = lon.toDoubleOrNull() ?: 0.0
            if (name.isNotBlank()) {
                addProductUseCase(name, latitude, longitude)
                onSuccess()
            }
        }
    }
}
