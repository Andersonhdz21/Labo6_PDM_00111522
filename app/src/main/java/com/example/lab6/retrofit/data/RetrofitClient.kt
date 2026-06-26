package com.example.lab6.retrofit.data

import com.example.lab6.common.data.MealResponseDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Definición de los endpoints
interface MealApiService {
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String = ""): MealResponseDTO
}

// Cliente estático tipo Singleton para proveer la instancia de la API
object RetrofitClient {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val apiService: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Usamos Gson para parsear
            .build()
            .create(MealApiService::class.java)
    }
}
