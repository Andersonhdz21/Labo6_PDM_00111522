package com.example.lab6.common.domain

// Modelo de dominio limpio, sin nulos innecesarios para la UI
data class Meal(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val imageUrl: String
)
