package ir.mrghost.famousplaces.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.mrghost.famousplaces.data.Place

@Composable
fun PlaceScreen(
    place: Place
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(place.image),
            contentDescription = place.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = place.name,
                style = MaterialTheme.typography.headlineLarge
            )
            HorizontalDivider(
                thickness = 4.dp,
                color = Color.Transparent
            )
            Text(
                text = place.detail,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}