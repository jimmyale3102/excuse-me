package dev.alejo.excuseme.ui.excuse

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.excuseme.R
import dev.alejo.excuseme.data.ExcuseModel
import dev.alejo.excuseme.ui.component.CategoryComponent
import dev.alejo.excuseme.ui.component.ExcuseButton
import dev.alejo.excuseme.ui.theme.DarkBlue
import dev.alejo.excuseme.ui.theme.Green

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExcuseScreen(viewModel: ExcuseViewModel) {
    val uiState: UIState by viewModel.uiState.observeAsState(UIState.Loading)
    val categories: List<String> = listOf("Funny", "Friends", "Random", "ASDDD", "Hey hey")
    val categoriesVisible: Boolean by viewModel.categoriesVisible.observeAsState(false)

    Box(
        Modifier
            .fillMaxSize()
            .background(color = DarkBlue)
            .padding(16.dp)
    ) {
        CategoryButton { viewModel.onCategoriesOpened() }
        if (categoriesVisible) {
            CategoryComponent(categories)
        }
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
                    ErrorMessage(
                        Modifier.align(Alignment.Center),
                        (uiState as UIState.Error).error.message.toString()
                    )
                }
            }
        }

        ExcuseOptions(Modifier.align(Alignment.BottomCenter)) { viewModel.onGetExcuse() }
    }
}

@Composable
fun CategoryButton(onCategoriesClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .padding(top = 32.dp),
        onClick = { onCategoriesClick() }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_category),
            tint = Green,
            contentDescription = stringResource(
                id = R.string.categories
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = R.string.categories), color = Color.White)
    }
}

@Composable
fun ExcuseLoading(modifier: Modifier) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
fun Excuse(modifier: Modifier, excuseData: ExcuseModel) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = excuseData.excuse,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(id = R.string.category),
                fontWeight = FontWeight.Bold,
                color = Green
            )
            Text(
                text = excuseData.category,
                color = Color.White
            )
        }
    }
}

@Composable
fun ErrorMessage(modifier: Modifier, errorMessage: String) {
    Text(
        modifier = modifier.padding(16.dp),
        text = errorMessage,
        textAlign = TextAlign.Center,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun ExcuseOptions(modifier: Modifier, onGetExcuse: () -> Unit) {
    Row(
        modifier = modifier.padding(bottom = 56.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ExcuseButton(
            painterResourceId = R.drawable.ic_copy,
            contentDescriptionId = R.string.copy_description
        ) {
            /** TODO **/
        }
        ExcuseButton(
            painterResourceId = R.drawable.ic_refresh,
            contentDescriptionId = R.string.refresh_icon_description
        ) { onGetExcuse() }
    }
}
