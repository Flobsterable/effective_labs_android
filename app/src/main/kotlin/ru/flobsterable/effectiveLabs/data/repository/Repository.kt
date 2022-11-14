package ru.flobsterable.effectiveLabs.data.repository

import kotlinx.coroutines.flow.Flow
import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

interface Repository {
    suspend fun getHeroesList(): Flow<Resource<List<HeroDataUi>>>
    suspend fun getHeroInfo(id: Int): Flow<Resource<HeroDataUi>>
}
