package ru.flobsterable.effectiveLabs.presentation.screens.heroesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.presentation.models.StateUi
import ru.flobsterable.effectiveLabs.ui.consts.heroesListColumnPaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListColumnPaddingPortrait
import ru.flobsterable.effectiveLabs.ui.consts.heroesListImagePaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListImagePaddingPortrait
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListEvent
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components.HeroesListView
import ru.flobsterable.effectiveLabs.presentation.screens.components.ErrorView
import ru.flobsterable.effectiveLabs.presentation.screens.components.LoadingView
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListUiState
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListViewModel
import ru.flobsterable.effectiveLabs.presentation.utils.isLandscape

private const val LANDSCAPE_IMAGE_WIDTH = 0.2f
private const val PORTRAIT_IMAGE_WIDTH = 1 / 3f


@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.sendEvent(HeroesListEvent.LoadHeroesList)
    })

    Column(
        modifier = when(isLandscape()){
            true -> Modifier.padding(heroesListColumnPaddingLandscape)
            false -> Modifier.padding(heroesListColumnPaddingPortrait)},
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = when(isLandscape()) {
                true -> Modifier
                    .fillMaxWidth(LANDSCAPE_IMAGE_WIDTH)
                    .padding(heroesListImagePaddingLandscape)
                false -> Modifier
                    .fillMaxWidth(PORTRAIT_IMAGE_WIDTH)
                    .padding(heroesListImagePaddingPortrait) },
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = stringResource(id = R.string.cd_main_banner)
        )

        when(uiState.value.stateUi){
            StateUi.Error -> ErrorView()
            StateUi.Loading -> LoadingView()
            StateUi.Success -> HeroesListView(uiState.value.heroesList) {
                viewModel.sendEvent(HeroesListEvent.OpenHeroInfo(it))
            }
        }
    }
}
