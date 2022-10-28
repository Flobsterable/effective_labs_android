package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.presentation.models.ViewSubState

data class HeroesListViewState(
    val heroesList: List<HeroDataUi> = emptyList(),
    val subState: ViewSubState = ViewSubState.LOADING,
)
