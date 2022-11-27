package ru.flobsterable.effectiveLabs.data.network

import ru.flobsterable.effectiveLabs.data.network.model.Result
import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.data.network.model.CharacterListData
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(
    private val networkService : ServerApi
    ): NetworkService {

    override suspend fun getCharacterList(): Resource<List<Result>> {
        return when (val result = networkService.getHeroesList()) {
            is Resource.Error -> Resource.Error(result.message)
            is Resource.Success -> Resource.Success(result.data.data.results)
        }
    }

    override suspend fun getCharacterInfo(id: Int): Resource<Result> {
        return when (val result: Resource<CharacterListData> = networkService.getHeroInfo(id.toString())) {
            is Resource.Error -> Resource.Error(result.message)
            is Resource.Success -> Resource.Success(result.data.data.results[0])
        }
    }
}
