package com.example.lab6.ktor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab6.common.domain.Meal
import com.example.lab6.ktor.data.MealRepository
import com.example.lab6.ktor.data.MealRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KtorMealViewModel(
    private val repository: MealRepository = MealRepositoryImpl(),
) : ViewModel() {

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
            
            // Usamos el repositorio (Clean Architecture) que retorna un Result
            repository.searchMeals("beef").fold(
                onSuccess = { mealsList ->
                    _meals.value = mealsList
                },
                { exception ->
                    _errorMessage.value = "Error al obtener recetas: ${exception.message}"
                }
            )
            
            _isLoading.value = false
        }
    }
}
