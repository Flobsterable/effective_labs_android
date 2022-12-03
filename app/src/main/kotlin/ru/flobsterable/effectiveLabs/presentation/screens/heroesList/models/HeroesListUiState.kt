package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.presentation.models.StateUi

data class HeroesListUiState(
    val heroesList: List<HeroDataUi> = emptyList(),
    val stateUi: StateUi = StateUi.Loading,
    val isRefreshing: Boolean = false,
) {
    companion object {
        val Empty = HeroesListUiState()
    }
}
