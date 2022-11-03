package ru.flobsterable.effectiveLabs.data.network

import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.data.network.model.Result

interface NetworkService {
    suspend fun getCharacterList(): Resource<List<Result>>
    suspend fun getCharacterInfo(id: Int): Resource<Result>
}
