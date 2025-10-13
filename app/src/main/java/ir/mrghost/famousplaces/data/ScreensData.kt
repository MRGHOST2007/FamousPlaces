package ir.mrghost.famousplaces.data

import kotlinx.serialization.Serializable

sealed class ScreensData() {
    @Serializable
    data object ScreenCategory

    @Serializable
    data class ScreenPlacesList(
        val category: Category
    )

    @Serializable
    data class ScreenPlaceInfo(
        val place: Place
    )
}

