package ru.flobsterable.effectiveLabs.presentation.models

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.flobsterable.effectiveLabs.navigation.AppScreens
import ru.flobsterable.effectiveLabs.navigation.DETAIL_ARGUMENT_KEY
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.HeroInfoScreen
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.HeroesListScreen
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models.HeroInfoViewModel
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListViewModel

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.HeroesListScreen.route
    ) {
        composable(route = AppScreens.HeroesListScreen.route) {
            val viewModel = hiltViewModel<HeroesListViewModel>()
            HeroesListScreen(viewModel)
        }
        composable(
            route = "${AppScreens.HeroInfoScreen.route}/{$DETAIL_ARGUMENT_KEY}",
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt(DETAIL_ARGUMENT_KEY)!!.toInt()
            val viewModel = hiltViewModel<HeroInfoViewModel>()
            HeroInfoScreen(viewModel, id = id)
        }
    }
}
