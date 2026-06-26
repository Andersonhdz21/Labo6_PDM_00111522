package com.example.lab6.retrofit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab6.common.data.toModel
import com.example.lab6.common.domain.Meal
import com.example.lab6.retrofit.data.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RetrofitMealViewModel : ViewModel() {

    // Exponemos los estados de UI usando StateFlow (buena práctica en Jetpack Compose)
    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> = _meals.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchMeals()
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Consumimos el cliente estático de Retrofit
                val response = RetrofitClient.apiService.searchMeals("chicken")
                val mealsDtoList = response.meals ?: emptyList()
                // Mapeamos los DTOs al modelo de dominio usando la extensión
                _meals.value = mealsDtoList.map { it.toModel() }
            } catch (e: Exception) {
                _errorMessage.value = "Error al obtener recetas: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
