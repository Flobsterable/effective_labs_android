package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.presentation.models.ViewSubState

data class HeroInfoViewState(
    val heroData: HeroDataUi? = null,
    val subState: ViewSubState = ViewSubState.LOADING
)
