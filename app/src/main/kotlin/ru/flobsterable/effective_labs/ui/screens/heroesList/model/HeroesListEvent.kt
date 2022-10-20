package ru.flobsterable.effective_labs.ui.screens.heroesList.model

sealed class HeroesListEvent {
    object LoadHeroesList : HeroesListEvent()
}
