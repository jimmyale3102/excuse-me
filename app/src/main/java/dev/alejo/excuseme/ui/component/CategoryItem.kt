package dev.alejo.excuseme.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.alejo.excuseme.R
import dev.alejo.excuseme.ui.excuse.CategoryAction
import dev.alejo.excuseme.ui.theme.DarkBlue
import dev.alejo.excuseme.ui.theme.Green


@Composable
fun CategoryItem(categoryName: String, onCategoriesAction: (CategoryAction) -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = Color.White
        ),
        onClick = { onCategoriesAction(CategoryAction.Selected(categoryName)) }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_category),
            tint = DarkBlue,
            contentDescription = stringResource(
                id = R.string.categories
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = categoryName, color = DarkBlue)
        Spacer(modifier = Modifier.weight(1f))
    }
}