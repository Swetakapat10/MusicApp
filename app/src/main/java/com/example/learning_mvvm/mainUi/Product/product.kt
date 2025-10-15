package com.example.learning_mvvm.mainUi.Product


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton


@Serializable
data class Category(
    val id: Int,
    val name: String,
    val slug: String,
    val image: String,
    val creationAt: String, // Use String if not using a custom serializer
    val updatedAt: String
)

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Double,
    val description: String,
    val category: Category,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String
)

@Composable

fun productScreen(viewModel: Product1ViewModel = hiltViewModel()) {
    val product by viewModel.Product.collectAsState()

    LazyColumn {
        items(product) { product ->
            Card(
                modifier = Modifier.padding(12.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Product ID: ${product.id}")
                    Text("Title: ${product.title}")
                    Text("Slug: ${product.slug}")
                    Text("Description: ${product.description}")
                    Text("Category: ${product.category.name}")

                    // Show the first image (if available)
                    val imageUrl = product.images.firstOrNull()
                    if (imageUrl != null) {
                        AsyncImage(
                            model = imageUrl, contentDescription = "Product Image"
                        )
                    }
                }
            }
        }
    }
}


@HiltViewModel
class Product1ViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _product = MutableStateFlow<List<Product>>(emptyList())
    val Product: StateFlow<List<Product>> = _product

    init {
        fetchProduct()
    }

    private fun fetchProduct() {
        viewModelScope.launch {
            _product.value = repository.fetchProduct()
        }
    }
}

@Singleton
class ProductRepository @Inject constructor(
    private val client: HttpClient
) {
    suspend fun fetchProduct(): List<Product> {
        val response: String = client.get("https://api.escuelajs.co/api/v1/products").bodyAsText()
        return Json.decodeFromString(response)
    }
}

/*@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}*/