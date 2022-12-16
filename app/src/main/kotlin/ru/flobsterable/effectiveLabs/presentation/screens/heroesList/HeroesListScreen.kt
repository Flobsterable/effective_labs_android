package ru.flobsterable.effectiveLabs.presentation.screens.heroesList

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
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
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListViewModel
import ru.flobsterable.effectiveLabs.presentation.utils.WindowSizeInfo
import ru.flobsterable.effectiveLabs.presentation.utils.getWindowInfo
import ru.flobsterable.effectiveLabs.presentation.utils.isLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListCompactPaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListCompactPaddingPortrait
import ru.flobsterable.effectiveLabs.ui.consts.heroesListExpandedPaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListExpandedPaddingPortrait
import ru.flobsterable.effectiveLabs.ui.consts.heroesListMediumPaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListMediumPaddingPortrait

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState()
    val ptrState= rememberPullRefreshState(uiState.value.isRefreshing,
        {viewModel.sendEvent(HeroesListEvent.LoadHeroesList)})

    val landscapePadding = when (getWindowInfo().screenWidthInfo) {
        WindowSizeInfo.WindowSizeType.Compact -> heroesListCompactPaddingLandscape
        WindowSizeInfo.WindowSizeType.Expanded -> heroesListExpandedPaddingLandscape
        WindowSizeInfo.WindowSizeType.Medium -> heroesListMediumPaddingLandscape
    }

    val portraitPadding = when (getWindowInfo().screenHeightInfo) {
        WindowSizeInfo.WindowSizeType.Compact -> heroesListCompactPaddingPortrait
        WindowSizeInfo.WindowSizeType.Expanded -> heroesListExpandedPaddingPortrait
        WindowSizeInfo.WindowSizeType.Medium -> heroesListMediumPaddingPortrait
    }

    Box(Modifier
        .pullRefresh(ptrState)
        .fillMaxSize()) {
        Column(
            when (isLandscape()) {
                true -> Modifier.padding(landscapePadding)
                false -> Modifier.padding(portraitPadding)
            }
                .fillMaxSize()
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
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
