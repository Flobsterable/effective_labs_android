package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

sealed class HeroesListUiState {
    data class Success(val heroesList: List<HeroDataUi>) : HeroesListUiState()
    object Error : HeroesListUiState()
    object Loading : HeroesListUiState()
}




