package ru.flobsterable.effectiveLabs.data.network

import ru.flobsterable.effectiveLabs.data.network.model.CharacterListData
import retrofit2.http.GET
import retrofit2.http.Path
import ru.flobsterable.effectiveLabs.data.models.Resource

interface ServerApi {
    @GET("v1/public/characters")
    suspend fun getHeroesList(): Resource<CharacterListData>

    @GET("v1/public/characters/{characterId} ")
    suspend fun getHeroInfo(@Path("characterId") id: String ): Resource<CharacterListData>
}
