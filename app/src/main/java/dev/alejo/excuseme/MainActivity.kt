package dev.alejo.excuseme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
                ExcuseScreen(viewModel)
            }
        }
    }
}