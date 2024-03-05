package dev.alejo.excuseme.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.alejo.excuseme.R
import dev.alejo.excuseme.ui.theme.DarkBlue
import dev.alejo.excuseme.ui.theme.Green

@Composable
fun ExcuseButton(
    @DrawableRes painterResourceId: Int,
    @StringRes contentDescriptionId: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = painterResourceId),
            tint = DarkBlue,
            contentDescription = stringResource(id = contentDescriptionId)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ExcuseFABPreview() {
    ExcuseButton(
        painterResourceId = R.drawable.ic_refresh,
        contentDescriptionId = R.string.refresh_icon_description
    ) {  }
}