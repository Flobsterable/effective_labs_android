package ru.flobsterable.effectiveLabs.navigation

import androidx.navigation.NavHostController

interface AppNavigation {
    var navHostController: NavHostController?

    fun navigateTo(appScreen: AppScreens, arg: String? = null)
    fun navigateTo(appScreen: AppScreens)
    fun popBackStack()
}
