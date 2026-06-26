package com.example.lab6.common.data

import com.example.lab6.common.domain.Meal

// Función de extensión para convertir el DTO al Modelo de Dominio de forma limpia
fun MealDTO.toModel(): Meal {
    return Meal(
        id = this.idMeal.orEmpty(),
        name = this.strMeal.orEmpty(),
        category = this.strCategory.orEmpty(),
        area = this.strArea.orEmpty(),
        imageUrl = this.strMealThumb.orEmpty()
    )
}
