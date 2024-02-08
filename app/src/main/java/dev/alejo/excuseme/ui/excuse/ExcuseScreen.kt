package dev.alejo.excuseme.ui.excuse

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.excuseme.R
import dev.alejo.excuseme.data.ExcuseModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExcuseScreen(viewModel: ExcuseViewModel) {
    val uiState: UIState by viewModel.uiState.observeAsState(UIState.Loading)

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AnimatedContent(
            modifier = Modifier.align(Alignment.Center),
            targetState = uiState,
            label = ""
        ) { targetState ->
            when (targetState) {
                is UIState.Loading -> {
                    ExcuseLoading(Modifier.align(Alignment.Center))
                }

                is UIState.Success -> {
                    Excuse(
                        Modifier.align(Alignment.Center),
                        (uiState as UIState.Success).excuseData
                    )
                }

                is UIState.Error -> {

                }
            }
        }

        ExcuseOptions(Modifier.align(Alignment.BottomCenter)) { viewModel.onGetExcuse() }
    }
}

@Composable
fun ExcuseLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
fun Excuse(modifier: Modifier, excuseData: ExcuseModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = excuseData.excuse,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Category:",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = excuseData.category
            )
        }
    }
}

@Composable
fun ExcuseOptions(modifier: Modifier, onGetExcuse: () -> Unit) {
    Row(
        modifier = modifier.padding(bottom = 56.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.excuse_me)) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_refresh),
                    contentDescription = stringResource(id = R.string.excuse_me_description)
                )
            },
            onClick = { onGetExcuse() }
        )
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(id = R.string.copy)) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = stringResource(id = R.string.copy_description)
                )
            },
            onClick = { /*TODO*/ }
        )
    }
}