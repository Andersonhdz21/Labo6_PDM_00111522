package com.example.lab6.ktor.data

import com.example.lab6.common.data.MealResponseDTO
import com.example.lab6.common.data.toModel
import com.example.lab6.common.domain.Meal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

// Interfaz que define el contrato del repositorio
interface MealRepository {
    suspend fun searchMeals(query: String): Result<List<Meal>>
}

// Implementación del repositorio usando Ktor
class MealRepositoryImpl(
    private val client: HttpClient = KtorClient.httpClient,
) : MealRepository {

    override suspend fun searchMeals(query: String): Result<List<Meal>> {
        return try {
            // Hacemos la petición GET con Ktor
            val response: MealResponseDTO = client.get("https://www.themealdb.com/api/json/v1/1/search.php") {
                url {
                    parameters.append("s", query)
                }
            }.body()

            val mealsList = response.meals?.map { it.toModel() } ?: emptyList()
            Result.success(mealsList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
