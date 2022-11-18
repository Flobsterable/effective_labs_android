package ru.flobsterable.effectiveLabs.data.repository

import ru.flobsterable.effectiveLabs.data.database.entity.HeroDataEntity
import ru.flobsterable.effectiveLabs.data.network.model.Result
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

interface ParserData {
    fun List<Result>.parserHeroDataList(): List<HeroDataEntity> {
        return this.map { it.parserHeroData() }
    }

    fun Result.parserHeroData(): HeroDataEntity {
        val url = (this.thumbnail.path + "." + this.thumbnail.extension).toHttpsPrefix()

        return HeroDataEntity(
            id = this.id.toInt(),
            name = this.name,
            description = this.description,
            uriImage = url
        )
    }

    fun List<HeroDataEntity>.parserHeroesDataUi(): List<HeroDataUi> {
        return this.map { it.parserHeroDataUi() }
    }

    fun HeroDataEntity.parserHeroDataUi(): HeroDataUi {
        return HeroDataUi(
            id = this.id,
            name = this.name,
            description = this.description,
            imageUrl = this.uriImage
        )
    }

    private fun String.toHttpsPrefix(): String =
        if (startsWith("http://")) replace("http://", "https://") else this
}
