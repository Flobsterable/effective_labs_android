package ru.flobsterable.effectiveLabs.ui.screens.heroesList.model

sealed class HeroesListEvent {
    object LoadHeroesList : HeroesListEvent()
}
