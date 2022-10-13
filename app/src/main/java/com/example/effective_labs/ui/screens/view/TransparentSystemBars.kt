package com.example.effective_labs.ui.screens.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.example.effective_labs.ui.theme.BlackScrim
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = false,
            transformColorForLightContent = { original ->
                BlackScrim.compositeOver(original)
            }
        )
    }
}
