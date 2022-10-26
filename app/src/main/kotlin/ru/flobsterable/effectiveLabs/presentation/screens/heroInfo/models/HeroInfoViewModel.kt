package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.flobsterable.effectiveLabs.navigation.AppNavigation
import ru.flobsterable.effectiveLabs.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.flobsterable.effectiveLabs.presentation.models.ViewSubState
import ru.flobsterable.effectiveLabs.presentation.utils.EventHandler
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor(
    private val navigation: AppNavigation,
    private val repository: Repository,
) : ViewModel(), EventHandler<HeroInfoEvent> {

    private val _viewState = MutableLiveData(HeroInfoViewState())
    val viewState: LiveData<HeroInfoViewState> = _viewState

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
        _viewState.value = HeroInfoViewState(subState = ViewSubState.LOADING)

        viewModelScope.launch {
            val heroData = repository.getHeroInfo(id)

            when (heroData!= null) {
                true -> {
                    _viewState.value = HeroInfoViewState(
                        heroData = heroData, subState = ViewSubState.COMPLETE
                    )
                }
                false -> _viewState.value = HeroInfoViewState(subState = ViewSubState.ERROR)
            }
        }
    }
}
