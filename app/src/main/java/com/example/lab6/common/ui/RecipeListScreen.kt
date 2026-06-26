package com.example.lab6.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab6.common.domain.Meal

@Composable
fun RecipeListScreen(
    meals: List<Meal>,
    isLoading: Boolean,
    errorMessage: String?,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Iteramos sobre nuestra lista limpia de dominio
                items(meals) { meal ->
                    MealCard(meal = meal)
                }
            }
        }
    }
}

@Composable
fun MealCard(meal: Meal) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Coil se encarga de descargar y cachear la imagen asíncronamente
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = "Imagen de la receta ${meal.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Categoría: ${meal.category}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Origen: ${meal.area}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
