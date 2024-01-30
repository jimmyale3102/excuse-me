package dev.alejo.excuseme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import dev.alejo.excuseme.ui.excuse.ExcuseScreen
import dev.alejo.excuseme.ui.excuse.ExcuseViewModel
import dev.alejo.excuseme.ui.theme.ExcuseMeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ExcuseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ExcuseMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExcuseScreen(viewModel)
                }
            }
        }
    }
}