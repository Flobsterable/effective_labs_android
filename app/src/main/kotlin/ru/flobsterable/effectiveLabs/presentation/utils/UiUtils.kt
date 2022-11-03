package ru.flobsterable.effectiveLabs.presentation.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun orientationValue (
    landscapeValue: Any?,
    portraitValue: Any?
): Any? {

    return when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            landscapeValue
        }
        else -> {
            portraitValue
        }
    }
}
