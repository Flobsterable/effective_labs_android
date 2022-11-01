package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.flobsterable.effectiveLabs.presentation.utils.EventHandler
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel(), EventHandler<HeroInfoEvent> {

    private val _uiState = MutableStateFlow<HeroInfoUiState>(HeroInfoUiState.Loading)
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
        _uiState.value = HeroInfoUiState.Loading

        viewModelScope.launch {
            val heroData = repository.getHeroInfo(id)

            when (heroData!= null) {
                true -> _uiState.value = HeroInfoUiState.Success(heroData)
                false -> _uiState.value = HeroInfoUiState.Error
            }
        }
    }
}
