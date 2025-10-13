package ir.mrghost.famousplaces.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.mrghost.famousplaces.data.Category

@Composable
fun CategoryScreen(
    onCategoryClicked: (category: Category) -> Unit,
    data: List<Category>
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .fillMaxSize(),
    ) {
        items(data.size) {
            ListItems(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onCategoryClicked(data[it])
                        }
                    ),
                category = data[it]
            )
            HorizontalDivider()
        }
    }
}

@Composable
private fun ListItems(
    category: Category,
    modifier: Modifier
) {

    Box(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(category.name),
                style = MaterialTheme.typography.headlineMedium,
            )
            HorizontalDivider(
                thickness = 4.dp,
                color = Color.Transparent
            )
            Text(
                text = category.detail,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

//@Preview(
//    showBackground = true
//)
//@Composable
//fun Test1(){
//    CategoryScreen {  }
//}