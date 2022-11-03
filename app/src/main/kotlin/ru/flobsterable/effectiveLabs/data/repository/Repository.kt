package ru.flobsterable.effectiveLabs.data.repository

import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

interface Repository {
    suspend fun getHeroesList(): Resource<List<HeroDataUi>>
    suspend fun getHeroInfo(id: Int): Resource<HeroDataUi>
}
