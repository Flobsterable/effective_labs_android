package ru.flobsterable.effectiveLabs.presentation.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

private const val DEVICE_MAX_WIDTH_COMPACT = 600
private const val DEVICE_MAX_WIDTH_MEDIUM = 840
private const val DEVICE_MAX_HEIGHT_COMPACT = 480
private const val DEVICE_MAX_HEIGHT_MEDIUM = 900

@Composable
fun isLandscape () = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

@Composable
fun getWindowInfo(): WindowSizeInfo {
    val configuration = LocalConfiguration.current
    return WindowSizeInfo(
        screenWidthInfo = when {
            configuration.screenWidthDp < DEVICE_MAX_WIDTH_COMPACT -> WindowSizeInfo.WindowSizeType.Compact
            configuration.screenWidthDp < DEVICE_MAX_WIDTH_MEDIUM -> WindowSizeInfo.WindowSizeType.Medium
            else -> WindowSizeInfo.WindowSizeType.Expanded
        },
        screenHeightInfo = when {
            configuration.screenHeightDp < DEVICE_MAX_HEIGHT_COMPACT -> WindowSizeInfo.WindowSizeType.Compact
            configuration.screenHeightDp < DEVICE_MAX_HEIGHT_MEDIUM -> WindowSizeInfo.WindowSizeType.Medium
            else -> WindowSizeInfo.WindowSizeType.Expanded
        },
    )
}
