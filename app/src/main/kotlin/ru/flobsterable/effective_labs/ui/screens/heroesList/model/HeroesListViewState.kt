package ru.flobsterable.effective_labs.ui.screens.heroesList.model

import ru.flobsterable.effective_labs.ui.model.HeroDataUi

data class HeroesListViewState(
    val heroesList: List<HeroDataUi> = emptyList()
)
