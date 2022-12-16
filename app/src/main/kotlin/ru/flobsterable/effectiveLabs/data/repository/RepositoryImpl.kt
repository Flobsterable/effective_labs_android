package ru.flobsterable.effectiveLabs.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.flobsterable.effectiveLabs.data.database.DataDao
import ru.flobsterable.effectiveLabs.data.database.entity.HeroDataEntity
import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.data.network.NetworkService
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val network: NetworkService,
    private val database: DataDao,
) : Repository, ParserData {


    override suspend fun getHeroesList(): Flow<Resource<List<HeroDataUi>>> = flow {

        val data = database.getCharactersList().first()
        if (data.isNotEmpty())
            emit(Resource.Success(data.parserHeroesDataUi()))
        when(val result = network.getCharacterList()){
            is Resource.Error -> if(data.isEmpty()) emit(Resource.Error(result.message))
            is Resource.Success -> {
                saveIntoCache(result.data.parserHeroDataList())
                database.getCharactersList().collect{ emit(Resource.Success(it.parserHeroesDataUi())) }
            }
        }
    }

    override suspend fun getHeroInfo(id: Int): Flow<Resource<HeroDataUi>> = flow {
        val data = database.getCharacterInfo(id).firstOrNull()
        if (data!=null)
            emit(Resource.Success(data.parserHeroDataUi()))

        when(val result = network.getCharacterInfo(id)){
            is Resource.Error -> if(data==null) emit(Resource.Error(result.message))
            is Resource.Success -> {
                database.insertHeroData(result.data.parserHeroData())
                database.getCharacterInfo(id).map{ emit(Resource.Success(it.parserHeroDataUi())) }
            }
        }
    }

    private suspend fun saveIntoCache(heroesList: List<HeroDataEntity>) {
        database.deleteAllHeroesList()
        database.insertHeroesList(heroesList)
    }
}
