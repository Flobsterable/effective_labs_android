package ru.flobsterable.effectiveLabs.presentation.screens.heroesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.ui.consts.heroesListColumnPaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListColumnPaddingPortrait
import ru.flobsterable.effectiveLabs.ui.consts.heroesListImagePaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListImagePaddingPortrait
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListEvent
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components.HeroesListView
import ru.flobsterable.effectiveLabs.presentation.screens.components.ErrorView
import ru.flobsterable.effectiveLabs.presentation.screens.components.LoadingView
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListUiState
import ru.flobsterable.effectiveLabs.presentation.utils.orientationModifier
import ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models.HeroesListViewModel

private const val LANDSCAPE_IMAGE_WIDTH = 0.2f
private const val PORTRAIT_IMAGE_WIDTH = 1 / 3f


@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel = viewModel()) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(HeroesListEvent.LoadHeroesList)
    })

    Column(
        modifier = orientationModifier(
            landscapeModifier = Modifier.padding(heroesListColumnPaddingLandscape),
            portraitModifier = Modifier.padding(heroesListColumnPaddingPortrait)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = orientationModifier(
                landscapeModifier = Modifier
                    .fillMaxWidth(LANDSCAPE_IMAGE_WIDTH)
                    .padding(heroesListImagePaddingLandscape),
                portraitModifier = Modifier
                    .fillMaxWidth(PORTRAIT_IMAGE_WIDTH)
                    .padding(heroesListImagePaddingPortrait)
            ),
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = stringResource(id = R.string.cd_main_banner)
        )

        when(uiState.value){
            is HeroesListUiState.Error -> ErrorView()
            HeroesListUiState.Loading -> LoadingView()
            is HeroesListUiState.Success -> HeroesListView(
                (uiState.value as HeroesListUiState.Success).heroesList) {
                    viewModel.obtainEvent(HeroesListEvent.OpenHeroInfo(it))
               }
        }
    }
}
