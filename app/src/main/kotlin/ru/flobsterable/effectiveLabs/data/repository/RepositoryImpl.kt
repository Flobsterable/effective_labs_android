package ru.flobsterable.effectiveLabs.data.repository

import android.content.Context
import ru.flobsterable.effectiveLabs.data.network.NetworkService
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.utils.hasInternetConnection
import ru.flobsterable.effectiveLabs.data.network.model.Result
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val appContext: Context,
    private val network: NetworkService,
) : Repository {

    override suspend fun getHeroesList(): List<HeroDataUi> {
        val heroesList = getCharacterListNetwork()
        return when(heroesList!=emptyList<Result>()){
            true -> parserHeroDataList(heroesList)
            false -> emptyList()
        }
    }

    override suspend fun getHeroInfo(id: Int): HeroDataUi? {
        val heroData = getCharacterInfoNetwork(id)
        return when (heroData!= null){
            true -> parserHeroData(heroData)
            false -> null
        }
    }

    private suspend fun getCharacterInfoNetwork(id: Int): Result? {
        return when (hasInternetConnection(appContext)) {
            true -> { network.getCharacterInfo(id) }
            false -> { null }
        }
    }

    private suspend fun getCharacterListNetwork(): List<Result> {
        return when (hasInternetConnection(appContext)) {
            true -> { network.getCharacterList() }
            false -> { emptyList() }
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
