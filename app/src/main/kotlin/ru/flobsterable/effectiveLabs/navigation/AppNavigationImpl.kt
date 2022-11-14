package ru.flobsterable.effectiveLabs.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

class AppNavigationImpl @Inject constructor() : AppNavigation {
    override var navHostController: NavHostController? = null

    override fun navigateTo(appScreen: AppScreens, arg: String?) {
        val route = "${appScreen.route}/$arg"
        navHostController?.navigate(route)
    }

    override fun navigateTo(appScreen: AppScreens) {
        navHostController?.navigate(appScreen.route)
    }

    override fun popBackStack() {
        navHostController?.popBackStack()
    }
}
