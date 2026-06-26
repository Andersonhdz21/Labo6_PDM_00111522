package com.example.lab6.common.data

import com.google.gson.annotations.SerializedName

// DTO para la respuesta completa de la API
data class MealResponseDTO(
    @SerializedName("meals") val meals: List<MealDTO>?
)

// DTO para cada receta que viene en el JSON
data class MealDTO(
    @SerializedName("idMeal") val idMeal: String?,
    @SerializedName("strMeal") val strMeal: String?,
    @SerializedName("strCategory") val strCategory: String?,
    @SerializedName("strArea") val strArea: String?,
    @SerializedName("strMealThumb") val strMealThumb: String?
)
