package ru.flobsterable.effectiveLabs.presentation.utils

data class WindowSizeInfo(
    val screenWidthInfo: WindowSizeType,
    val screenHeightInfo: WindowSizeType,
) {
    sealed class WindowSizeType {
        object Compact : WindowSizeType()
        object Medium : WindowSizeType()
        object Expanded : WindowSizeType()
    }
}
