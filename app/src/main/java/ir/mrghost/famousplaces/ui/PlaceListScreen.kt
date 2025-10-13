package ir.mrghost.famousplaces.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.mrghost.famousplaces.data.Place

@Composable
fun PlaceListScreen(
    onPlaceClicked: (place: Place) -> Unit,
    data: List<Place>,
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxSize(),
    ) {
        items(data.size) {
            val item = data[it]
            ListItems(
                place = item,
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onPlaceClicked(item)
                        }
                    )
            )
            HorizontalDivider()
        }
    }
}


@Composable
private fun ListItems(
    place: Place,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(place.image),
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(72.dp)
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    )
            )

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.headlineMedium,
                )
                HorizontalDivider(
                    thickness = 4.dp,
                    color = Color.Transparent
                )
                Text(
                    text = place.detail,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

//@Preview(
//    showBackground = true
//)
//@Composable
//fun Test2(){
//    PlaceListScreen({
//
//    },
//        category = Categories.Fastfoods
//    )
//}