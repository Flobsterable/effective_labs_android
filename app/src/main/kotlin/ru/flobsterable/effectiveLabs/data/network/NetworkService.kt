package ru.flobsterable.effectiveLabs.data.network

import ru.flobsterable.effectiveLabs.data.network.model.Result

interface NetworkService {
    suspend fun getCharacterList(): List<Result>
    suspend fun getCharacterInfo(id: Int): Result?
}
