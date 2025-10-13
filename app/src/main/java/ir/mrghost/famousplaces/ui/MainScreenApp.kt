package ir.mrghost.famousplaces.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.mrghost.famousplaces.R
import ir.mrghost.famousplaces.data.Category
import ir.mrghost.famousplaces.data.Place
import ir.mrghost.famousplaces.data.PlacesViewModel
import ir.mrghost.famousplaces.data.ScreensData
import ir.mrghost.famousplaces.data.parcelableType
import kotlin.reflect.typeOf

@Composable
fun MainApp() {

    val navController = rememberNavController()
    val viewModel: PlacesViewModel = viewModel()
    var title by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppBar(
                title = title,
                hasBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = ScreensData.ScreenCategory,
            modifier = Modifier
                .padding(innerPadding)
        ) {

            composable<ScreensData.ScreenCategory>(
                typeMap = mapOf(typeOf<Category>() to parcelableType<Category>())
            ) {
                title = stringResource(id = R.string.app_name)
                val data = viewModel.getCategories()
                CategoryScreen(
                    data = data,
                    onCategoryClicked = { clickedCategory ->
                        navController.navigate(
                            ScreensData.ScreenPlacesList(
                                category = clickedCategory
                            )
                        )
                    })
            }

            composable<ScreensData.ScreenPlacesList>(
                typeMap = mapOf(typeOf<Category>() to parcelableType<Category>())
            ) { args ->
                val item = args.toRoute<ScreensData.ScreenPlacesList>()
                title = stringResource(item.category.name)
                val data = viewModel.getPlaces(item.category.name)

                PlaceListScreen(
                    data = data,
                    onPlaceClicked = { clickedPlace ->
                        navController.navigate(
                            ScreensData.ScreenPlaceInfo(
                                place = clickedPlace
                            )
                        )
                    }
                )
            }

            composable<ScreensData.ScreenPlaceInfo>(
                typeMap = mapOf(typeOf<Place>() to parcelableType<Place>())
            ) {
                val args = it.toRoute<ScreensData.ScreenPlaceInfo>()
                title = args.place.name
                PlaceScreen(
                    place = args.place
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    hasBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            if (hasBack) {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Null"
                    )
                }
            }
        }
    )
}