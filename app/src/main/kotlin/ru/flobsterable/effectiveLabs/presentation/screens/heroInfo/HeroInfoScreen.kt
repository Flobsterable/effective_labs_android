package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import ru.flobsterable.effectiveLabs.presentation.screens.components.LoadingView
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.flobsterable.effectiveLabs.presentation.models.StateUi
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models.HeroInfoEvent
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.components.HeroInfoView
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.components.HeroScreenTopBar
import ru.flobsterable.effectiveLabs.presentation.screens.components.ErrorView
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models.HeroInfoViewModel


@Composable
fun HeroInfoScreen(viewModel: HeroInfoViewModel = viewModel(), id: Int) {

    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.sendEvent(HeroInfoEvent.LoadHeroInfo(id))
    })

    when (uiState.value.stateUi) {
        StateUi.Error -> ErrorView()
        StateUi.Loading -> LoadingView()
        StateUi.Success -> HeroInfoView(heroInfo = uiState.value.heroInfo!!)
    }

    HeroScreenTopBar {
        viewModel.sendEvent(HeroInfoEvent.PopBack)
    }
}
