package dev.alejo.excuseme.ui.quote

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.excuseme.R

@Composable
fun QuoteScreen() {
    val uiState: UIState = UIState.Success("")
    //val uiState: UIState = UIState.Loading
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(uiState) {
            is UIState.Loading -> {
                QuoteLoading(Modifier.align(Alignment.Center))
            }
            is UIState.Success -> {
                Quote(Modifier.align(Alignment.Center))
                QuoteOptions(Modifier.align(Alignment.BottomCenter))
            }
            is UIState.Error -> {  }
        }
    }
}

@Composable
fun QuoteLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
fun Quote(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "\"This is an example of quote\"",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Friends",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun QuoteOptions(modifier: Modifier) {
    Row(
        modifier = modifier.padding(bottom = 56.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_refresh),
                contentDescription = null
            )
        }
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_copy),
                contentDescription = null
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun QuoteScreenPreview() {
    QuoteScreen()
}