package ru.flobsterable.effective_labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.flobsterable.effective_labs.ui.screens.heroesList.HeroesListScreen
import ru.flobsterable.effective_labs.ui.screens.view.TransparentSystemBars
import ru.flobsterable.effective_labs.ui.theme.EffectiveLabsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            EffectiveLabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.secondary
                ) {
                    TransparentSystemBars()
                    HeroesListScreen()
                }
            }
        }
    }
}
