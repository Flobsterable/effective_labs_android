package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.navigation.AppScreens
import ru.flobsterable.effectiveLabs.data.repository.Repository
import ru.flobsterable.effectiveLabs.presentation.models.StateUi
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HeroesListUiState.Empty)
    val uiState : StateFlow<HeroesListUiState> = _uiState.asStateFlow()

    init {
        getHeroesList()
    }

    fun sendEvent(event: HeroesListEvent) {
        when (event) {
            HeroesListEvent.LoadHeroesList -> getHeroesList()
            is HeroesListEvent.OpenHeroInfo -> openHeroInfo(event.value)
        }
    }

    private fun getHeroesList() {
        _uiState.update { it.copy(isRefreshing = true) }
        viewModelScope.launch{
            repository.getHeroesList().catch {
                _uiState.update { it.copy(stateUi = StateUi.Error, isRefreshing = false) }
            }.collect{ resource->
                when(resource){
                    is Resource.Error -> _uiState.update { it.copy(isRefreshing = false, stateUi = StateUi.Error) }
                    is Resource.Success -> _uiState.update {
                        it.copy(isRefreshing = false, heroesList = resource.data, stateUi = StateUi.Success) }
                }
            }
        }
    }

    private fun openHeroInfo(id: Int) {
        navigation.navigateTo(AppScreens.HeroInfoScreen, id.toString())
    }
}
