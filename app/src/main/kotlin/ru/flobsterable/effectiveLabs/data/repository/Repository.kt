package ru.flobsterable.effectiveLabs.data.repository

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

interface Repository {
    suspend fun getHeroesList(): List<HeroDataUi>
    suspend fun getHeroInfo(id: Int): HeroDataUi?
}
