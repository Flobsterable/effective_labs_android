package ru.flobsterable.effectiveLabs.navigation

import androidx.navigation.NavHostController

interface AppNavigation {
    var navHostController: NavHostController?

    fun navigateTo(appScreen: AppScreens)
    fun navigateToWithArg(appScreen: AppScreens, arg: String)
    fun popBackStack()
}
