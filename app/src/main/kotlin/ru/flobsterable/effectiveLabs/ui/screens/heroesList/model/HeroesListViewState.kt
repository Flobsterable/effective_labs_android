package ru.flobsterable.effectiveLabs.ui.screens.heroesList.model

import ru.flobsterable.effectiveLabs.ui.model.HeroDataUi

data class HeroesListViewState(
    val heroesList: List<HeroDataUi> = emptyList()
)
