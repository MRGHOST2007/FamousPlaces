package ir.mrghost.famousplaces.data

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import ir.mrghost.famousplaces.R
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class DataSource() {

    val categories = listOf(
        Category(
            name = R.string.coffee_shops,
            detail = "Popular chain coffee shops near you!"
        ),
        Category(
            name = R.string.fast_foods,
            detail = "Popular chain fast foods near you!"
        ),
        Category(
            name = R.string.towers,
            detail = "Famous towers all around the world!"
        )
    )

    val coffeeShops = listOf(
        Place(
            name = "Starbucks",
            image = R.drawable.starbucks
        ),
        Place(
            name = "Dunkin",
            image = R.drawable.dunkin
        ),
        Place(
            name = "McCafe",
            image = R.drawable.mccafe
        )
    )

    val fastFoods = listOf(
        Place(
            name = "McDonald's",
            image = R.drawable.mcdonalds
        ),
        Place(
            name = "KFC",
            image = R.drawable.kfc
        ),
        Place(
            name = "Pizza Hut",
            image = R.drawable.pizzahut
        )
    )

    val towers = listOf(
        Place(
            name = "Eiffel Tower",
            image = R.drawable.eiffle,
            detail = "Paris, France"
        ),
        Place(
            name = "Shanghai Tower",
            image = R.drawable.shanghai,
            detail = "Shanghai, China"
        ),
        Place(
            name = "Milad Tower",
            image = R.drawable.milad,
            detail = "Tehran, Iran"
        )
    )

}

@Serializable
@Parcelize
data class Place(
    val name: String,
    val detail: String = "Null",
    @DrawableRes val image: Int
) : Parcelable

@Serializable
@Parcelize
data class Category(
    @StringRes val name: Int,
    val detail: String
) : Parcelable


inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun put(bundle: SavedState, key: String, value: T) =
        bundle.putParcelable(key, value)

    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T =
        json.decodeFromString(value)

    override fun serializeAsValue(value: T): String =
        Uri.encode(json.encodeToString(value))
}