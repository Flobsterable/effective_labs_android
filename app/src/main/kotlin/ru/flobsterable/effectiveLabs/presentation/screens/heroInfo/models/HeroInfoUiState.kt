package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

sealed class HeroInfoUiState {
    data class Success(val heroInfo: HeroDataUi) : HeroInfoUiState()
    object Error : HeroInfoUiState()
    object Loading : HeroInfoUiState()
}
