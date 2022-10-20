package ru.flobsterable.effective_labs.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun orientationModifier(
    landscapeModifier: Modifier,
    portraitModifier: Modifier
): Modifier {

    return when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            landscapeModifier
        }
        else -> {
            portraitModifier
        }
    }
}
