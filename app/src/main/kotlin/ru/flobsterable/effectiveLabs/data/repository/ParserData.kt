package ru.flobsterable.effectiveLabs.data.repository

import ru.flobsterable.effectiveLabs.data.network.model.Result
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi

interface ParserData {
    fun List<Result>.parserHeroDataList(): List<HeroDataUi> {
        val resultList = mutableListOf<HeroDataUi>()
        for (item in this) {
            resultList.add(item.parserHeroData()) }
        return resultList
    }

    fun Result.parserHeroData(): HeroDataUi {
        val url = (this.thumbnail.path + "." + this.thumbnail.extension).toHttpsPrefix()

        return HeroDataUi(
            id = this.id.toInt(),
            name = this.name,
            description = this.description,
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
