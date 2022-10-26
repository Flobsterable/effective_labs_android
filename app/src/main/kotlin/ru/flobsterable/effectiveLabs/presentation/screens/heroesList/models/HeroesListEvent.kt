package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

sealed class HeroesListEvent {
    object LoadHeroesList : HeroesListEvent()
    data class OpenHeroInfo(val value: Int) : HeroesListEvent()
}
