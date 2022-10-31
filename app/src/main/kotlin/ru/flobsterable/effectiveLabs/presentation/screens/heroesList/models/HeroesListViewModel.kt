package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.navigation.AppScreens
import ru.flobsterable.effectiveLabs.data.repository.Repository
import ru.flobsterable.effectiveLabs.presentation.models.ViewSubState
import ru.flobsterable.effectiveLabs.presentation.utils.EventHandler
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel(), EventHandler<HeroesListEvent> {

    private val _viewState = MutableStateFlow(HeroesListViewState())
    val viewState: StateFlow<HeroesListViewState> = _viewState.asStateFlow()

    override fun obtainEvent(event: HeroesListEvent) {
        when (event) {
            HeroesListEvent.LoadHeroesList -> getHeroesList()
            is HeroesListEvent.OpenHeroInfo -> openHeroInfo(event.value)
        }
    }

    private fun getHeroesList() {

        _viewState.value = HeroesListViewState(subState = ViewSubState.LOADING)
        viewModelScope.launch {
            val heroesList = repository.getHeroesList()

            when (heroesList.isNotEmpty()) {
                true -> {
                    _viewState.value = HeroesListViewState(
                        subState = ViewSubState.COMPLETE, heroesList = heroesList)
                }
                false -> {
                    _viewState.value = HeroesListViewState(subState = ViewSubState.ERROR)
                }
            }
        }
    }

    private fun openHeroInfo(id: Int) {
        navigation.navigateTo(AppScreens.HeroInfoScreen, id.toString())
    }
}
