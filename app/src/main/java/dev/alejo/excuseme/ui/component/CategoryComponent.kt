package dev.alejo.excuseme.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.alejo.excuseme.R
import dev.alejo.excuseme.ui.theme.Green

@Composable
fun CategoryComponent(categories: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(categories) { categoryName ->
            CategoryItem(categoryName = categoryName)
        }
    }
}

@Composable
fun CategoryItem(categoryName: String) {
    ExtendedFloatingActionButton(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Green,
                shape = FloatingActionButtonDefaults.extendedFabShape
            ),
        text = {
            Text(text = categoryName, color = Color.White)
        },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_category),
                tint = Green,
                contentDescription = stringResource(
                    id = R.string.categories
                )
            )
        },
        containerColor = Color.Transparent,
        onClick = { /** TODO **/ }
    )
}