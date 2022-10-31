package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import ru.flobsterable.effectiveLabs.presentation.screens.components.LoadingView
import ru.flobsterable.effectiveLabs.presentation.models.ViewSubState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models.HeroInfoEvent
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.components.HeroInfoView
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.components.HeroScreenTopBar
import ru.flobsterable.effectiveLabs.presentation.screens.components.ErrorView
import ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models.HeroInfoViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HeroInfoScreen(viewModel: HeroInfoViewModel = viewModel(), id: Int) {

    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.obtainEvent(HeroInfoEvent.LoadHeroInfo(id))
    })

    when (viewState.value.subState) {
        ViewSubState.LOADING -> {
            LoadingView()
        }
        ViewSubState.ERROR -> {
            ErrorView()
        }
        ViewSubState.COMPLETE -> {
            HeroInfoView(viewState.value.heroData!!)
        }
    }

    HeroScreenTopBar {
        viewModel.obtainEvent(HeroInfoEvent.PopBack)
    }
}
