package ru.flobsterable.effectiveLabs.presentation.screens.heroesList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.flobsterable.effectiveLabs.presentation.models.StateUi
import ru.flobsterable.effectiveLabs.presentation.screens.components.ErrorView
import ru.flobsterable.effectiveLabs.presentation.screens.components.LoadingView
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components.HeroesListRow
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components.ImageTitle
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListEvent
import ru.flobsterable.effectiveLabs.ui.consts.heroesListColumnPaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListColumnPaddingPortrait
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListViewModel
import ru.flobsterable.effectiveLabs.presentation.utils.isLandscape

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState()
    val ptrState= rememberPullRefreshState(uiState.value.isRefreshing,
        {viewModel.sendEvent(HeroesListEvent.LoadHeroesList)})

    Box( Modifier.pullRefresh(ptrState).fillMaxSize()) {

        Column(
            when (isLandscape()) {
                true -> Modifier.padding(heroesListColumnPaddingLandscape)
                false -> Modifier.padding(heroesListColumnPaddingPortrait)
            }
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageTitle()
            when (uiState.value.stateUi) {
                StateUi.Error -> ErrorView()
                StateUi.Loading -> LoadingView()
                StateUi.Success -> {
                    HeroesListRow(heroesList = uiState.value.heroesList) {
                        viewModel.sendEvent(HeroesListEvent.OpenHeroInfo(it))
                    }
                }
            }
        }
        PullRefreshIndicator(uiState.value.isRefreshing, ptrState, Modifier.align(Alignment.TopCenter))
    }
}
