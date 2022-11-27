package ru.flobsterable.effectiveLabs.presentation.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isLandscape () = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
