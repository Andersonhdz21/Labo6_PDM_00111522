package com.example.lab6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab6.common.ui.RecipeListScreen
import com.example.lab6.ktor.ui.KtorMealViewModel
import com.example.lab6.retrofit.ui.RetrofitMealViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    AppContent()
                }
            }
        }
    }
}

@Composable
fun AppContent() {
    // Un estado simple para cambiar entre las dos implementaciones en la UI
    var useRetrofit by remember { mutableStateOf(value = true) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = { useRetrofit = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (useRetrofit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Usar Retrofit")
            }
            Button(
                onClick = { useRetrofit = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!useRetrofit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text("Usar Ktor")
            }
        }

        // Se muestra la pantalla correspondiente según la selección
        if (useRetrofit) {
            val viewModel: RetrofitMealViewModel = viewModel()
            val meals by viewModel.meals.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val errorMessage by viewModel.errorMessage.collectAsState()

            RecipeListScreen(
                meals = meals,
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        } else {
            val viewModel: KtorMealViewModel = viewModel()
            val meals by viewModel.meals.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()
            val errorMessage by viewModel.errorMessage.collectAsState()

            RecipeListScreen(
                meals = meals,
                isLoading = isLoading,
                errorMessage = errorMessage
            )
        }
    }
}
