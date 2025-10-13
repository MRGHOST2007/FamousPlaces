package ir.mrghost.famousplaces.data

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ir.mrghost.famousplaces.R

class PlacesViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(PlacesUiState())
    val uiState = _uiState.asStateFlow()
    val dataSource = DataSource()

    fun getCategories(): List<Category> {
        return dataSource.categories
    }

    fun getPlaces(@StringRes category: Int): List<Place> {
        return when (category) {
            R.string.coffee_shops -> {
                dataSource.coffeeShops
            }

            R.string.fast_foods -> {
                dataSource.fastFoods
            }

            R.string.towers -> {
                dataSource.towers
            }
            else -> {
                emptyList()
            }
        }
    }
}

data class PlacesUiState(
    val selectedCategory: Category = Category(
        name = R.string.app_name,
        detail = "Null"
    ),
    val selectedPlace: Place = Place(
        name = "Null",
        detail = "Null",
        image = R.drawable.ic_launcher_foreground
    )
)