package ru.flobsterable.effectiveLabs.navigation

import androidx.navigation.NavHostController
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.navigation.AppScreens

class AppNavigationImpl : AppNavigation {
    override var navHostController: NavHostController? = null

    override fun navigateTo(appScreen: AppScreens) {
        navHostController?.navigate(appScreen.route)
    }

    override fun navigateToWithArg(appScreen: AppScreens, arg: String) {
        val route = "${appScreen.route}/$arg"
        navHostController?.navigate(route)
    }

    override fun popBackStack() {
        navHostController?.popBackStack()
    }
}
