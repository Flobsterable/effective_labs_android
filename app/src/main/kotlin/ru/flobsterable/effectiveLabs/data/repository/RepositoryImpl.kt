package ru.flobsterable.effectiveLabs.data.repository

import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    private val testList = listOf(
        HeroDataUi(
            "Iron Man",
            "A wealthy American business magnate, playboy, philanthropist, inventor and ingenious scientist",
            "https://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55.jpg",
            0,
        ),
        HeroDataUi(
            "Thanos",
            "The character's name is derived from the name Thanatos, the personification of death in Greek mythology.",
            "https://i.annihil.us/u/prod/marvel/i/mg/6/40/5274137e3e2cd.jpg",
            1,
        ),
        HeroDataUi(
            "Wolverine",
            "He is a mutant",
            "https://i.annihil.us/u/prod/marvel/i/mg/2/60/537bcaef0f6cf.jpg",
            2,
        ),
    )

    override suspend fun getHeroesList(): List<HeroDataUi> {
        return testList
    }

    override suspend fun getHeroInfo(id: Int): HeroDataUi? {
        for (item in testList){
            if(item.id == id)
                return item
        }
        return null
    }
}
