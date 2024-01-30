package dev.alejo.excuseme.ui.excuse

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.excuseme.R

@Composable
fun ExcuseScreen(viewModel: ExcuseViewModel) {
    val uiState: UIState = UIState.Success("")
    //val uiState: UIState = UIState.Loading

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when(uiState) {
            is UIState.Loading -> {
                ExcuseLoading(Modifier.align(Alignment.Center))
            }
            is UIState.Success -> {
                val excuse: String by viewModel.excuse.observeAsState("")
                Excuse(Modifier.align(Alignment.Center), excuse)
                ExcuseOptions(Modifier.align(Alignment.BottomCenter))
            }
            is UIState.Error -> {  }
        }
    }
}

@Composable
fun ExcuseLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
fun Excuse(modifier: Modifier, excuse: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = excuse,
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
fun ExcuseOptions(modifier: Modifier) {
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