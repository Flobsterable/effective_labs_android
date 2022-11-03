package ru.flobsterable.effectiveLabs.data.repository

import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.data.network.NetworkService
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.data.network.model.Result
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val network: NetworkService,
) : Repository {

    override suspend fun getHeroesList(): Resource<List<HeroDataUi>> {
        return when (val request = network.getCharacterList()){
            is Resource.Error -> Resource.Error(request.message!!)
            is Resource.Success -> Resource.Success(parserHeroDataList(request.data!!))
        }
    }

    override suspend fun getHeroInfo(id: Int): Resource<HeroDataUi> {
        return when (val request = network.getCharacterInfo(id) ){
            is Resource.Error -> Resource.Error(request.message!!)
            is Resource.Success -> Resource.Success(parserHeroData(request.data!!))
        }
    }

    private fun parserHeroDataList(list: List<Result>): List<HeroDataUi> {
        val resultList = mutableListOf<HeroDataUi>()
        for (item in list) {
            resultList.add(
                parserHeroData(item)
            )
        }
        return resultList
    }
    private fun parserHeroData(data: Result): HeroDataUi {
        val url = (data.thumbnail.path + "." + data.thumbnail.extension).toHttpsPrefix()

        return HeroDataUi(
            id = data.id.toInt(),
            name = data.name,
            description = data.description,
            imageUrl = url
        )
    }

    private fun String.toHttpsPrefix(): String =
        if (isNotEmpty() && !startsWith("https://") && !startsWith("http://")) {
        "https://$this"
    } else if (startsWith("http://")) {
        replace("http://", "https://")
    } else this
}
