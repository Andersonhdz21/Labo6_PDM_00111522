package com.example.lab6.ktor.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

object KtorClient {
    // Configuración del cliente HTTP de Ktor
    val httpClient = HttpClient(Android) {
        // Configuramos la negociación de contenido para que sepa parsear JSON usando Gson
        install(ContentNegotiation) {
            gson()
        }
    }
}
