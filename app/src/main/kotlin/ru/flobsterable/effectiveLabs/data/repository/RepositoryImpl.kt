package ru.flobsterable.effectiveLabs.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.data.network.NetworkService
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val network: NetworkService,
) : Repository, ParserData {

    override suspend fun getHeroesList(): Flow<Resource<List<HeroDataUi>>> = flow {
        when(val request = network.getCharacterList()) {
            is Resource.Error -> emit(Resource.Error(request.message.toString()))
            is Resource.Success -> emit(Resource.Success(request.data!!.parserHeroDataList()))
        }
    }.flowOn(Dispatchers.Default).catch { e->
        emit(Resource.Error(e.message.toString())) }

    override suspend fun getHeroInfo(id: Int): Flow<Resource<HeroDataUi>> = flow {
        when(val request = network.getCharacterInfo(id)) {
            is Resource.Error -> emit(Resource.Error(request.message.toString()))
            is Resource.Success -> emit(Resource.Success(request.data!!.parserHeroData()))
        }
    }.flowOn(Dispatchers.Default).catch { e->
        emit(Resource.Error(e.message.toString())) }
}
