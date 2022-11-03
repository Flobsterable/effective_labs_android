package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.presentation.models.StateUi

data class HeroInfoUiState(
    val heroInfo: HeroDataUi? = null,
    val stateUi : StateUi = StateUi.Loading

) {
    companion object {
        val Empty = HeroInfoUiState()
    }
}


