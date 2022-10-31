package ru.flobsterable.effectiveLabs.data.network

import ru.flobsterable.effectiveLabs.data.network.model.CharacterListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {
    @GET("v1/public/characters")
    suspend fun getHeroesList(
        @Query("apikey") apikey: String,
        @Query("hash")hash: String,
        @Query("ts") ts: Int
    ): Response<CharacterListData>

    @GET("v1/public/characters/{characterId} ")
    suspend fun getHeroInfo(
        @Path("characterId") id: String,
        @Query("apikey") apikey: String,
        @Query("hash")hash: String,
        @Query("ts") ts: Int,
    ): Response<CharacterListData>
}
