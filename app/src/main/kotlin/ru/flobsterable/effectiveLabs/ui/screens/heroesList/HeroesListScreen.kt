package ru.flobsterable.effectiveLabs.ui.screens.heroesList

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
import ru.flobsterable.effectiveLabs.ui.screens.heroesList.model.HeroesListEvent
import ru.flobsterable.effectiveLabs.ui.screens.heroesList.view.HeroesListView
import ru.flobsterable.effectiveLabs.utils.orientationModifier
import ru.flobsterable.effectiveLabs.viewModels.HeroesListViewModel

@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel = viewModel()) {

    val viewState = viewModel.viewState.collectAsState()

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
                    .fillMaxWidth(1 / 5f)
                    .padding(heroesListImagePaddingLandscape),
                portraitModifier = Modifier
                    .fillMaxWidth(1 / 3f)
                    .padding(heroesListImagePaddingPortrait)
            ),
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = stringResource(id = R.string.cd_main_banner)
        )

        HeroesListView(viewState.value.heroesList)
    }
}