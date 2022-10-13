package com.example.effective_labs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.effective_labs.ui.screens.heroesList.HeroesListScreen
import com.example.effective_labs.ui.screens.view.TransparentSystemBars
import com.example.effective_labs.ui.theme.Effective_labsTheme
import com.example.effective_labs.viewModels.HeroesListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Effective_labsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.secondary
                ) {
                    val viewModel = hiltViewModel<HeroesListViewModel>()
                    TransparentSystemBars()
                    HeroesListScreen(viewModel = viewModel)
                }
            }
        }
    }
}
