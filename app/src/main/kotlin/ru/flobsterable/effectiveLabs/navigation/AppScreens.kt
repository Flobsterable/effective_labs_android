package ru.flobsterable.effectiveLabs.navigation

const val DETAIL_ARGUMENT_KEY = "id"

sealed class AppScreens(
    val route: String
) {
    object HeroesListScreen : AppScreens("HeroesListScreen")
    object HeroInfoScreen : AppScreens("HeroInfoScreen")
}
