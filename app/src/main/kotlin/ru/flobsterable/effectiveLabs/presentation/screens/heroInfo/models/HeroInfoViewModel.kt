package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.flobsterable.effectiveLabs.data.models.Resource
import ru.flobsterable.effectiveLabs.presentation.models.StateUi
import ru.flobsterable.effectiveLabs.presentation.utils.EventHandler
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel(), EventHandler<HeroInfoEvent> {

    private val _uiState = MutableStateFlow(HeroInfoUiState.Empty)
    val uiState: StateFlow<HeroInfoUiState> = _uiState.asStateFlow()

    override fun obtainEvent(event: HeroInfoEvent) {
        when (event) {
            is HeroInfoEvent.LoadHeroInfo -> getHeroInfo(event.value)
            HeroInfoEvent.PopBack -> popBack()
        }
    }

    private fun popBack() {
        navigation.popBackStack()
    }

    private fun getHeroInfo(id: Int) {

        viewModelScope.launch {

            when(val heroData = repository.getHeroInfo(id)){
                is Resource.Error -> _uiState.update { it.copy(stateUi = StateUi.Error)}
                is Resource.Success -> _uiState.update { it.copy(stateUi = StateUi.Success, heroInfo = heroData.data)}
            }
        }
    }
}
