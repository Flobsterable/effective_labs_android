package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.navigation.AppScreens
import ru.flobsterable.effectiveLabs.data.repository.Repository
import ru.flobsterable.effectiveLabs.presentation.utils.EventHandler
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel(), EventHandler<HeroesListEvent> {

    private val _uiState = MutableStateFlow<HeroesListUiState>(HeroesListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    override fun obtainEvent(event: HeroesListEvent) {
        when (event) {
            HeroesListEvent.LoadHeroesList -> getHeroesList()
            is HeroesListEvent.OpenHeroInfo -> openHeroInfo(event.value)
        }
    }

    private fun getHeroesList() {
        _uiState.value = HeroesListUiState.Loading

        viewModelScope.launch {
            val heroesList = repository.getHeroesList()

            when (heroesList.isNotEmpty()) {
                true -> {
                    _uiState.value = HeroesListUiState.Success(heroesList)
                }
                false -> {
                    _uiState.value = HeroesListUiState.Error
                }
            }
        }
    }

    private fun openHeroInfo(id: Int) {
        navigation.navigateTo(AppScreens.HeroInfoScreen, id.toString())
    }
}
