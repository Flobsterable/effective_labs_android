package ru.flobsterable.effectiveLabs.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.ui.model.HeroDataUi
import ru.flobsterable.effectiveLabs.ui.screens.heroesList.model.HeroesListEvent
import ru.flobsterable.effectiveLabs.ui.screens.heroesList.model.HeroesListViewState
import ru.flobsterable.effectiveLabs.utils.EventHandler
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor() : ViewModel(), EventHandler<HeroesListEvent> {

    private val _viewState = MutableStateFlow(HeroesListViewState())
    val viewState: StateFlow<HeroesListViewState> = _viewState.asStateFlow()

    private val testList = listOf(
        HeroDataUi(
            "Iron Man",
            "A wealthy American business magnate, playboy, philanthropist, inventor and ingenious scientist",
            R.drawable.iron_man
        ),
        HeroDataUi(
            "Thanos",
            "The character's name is derived from the name Thanatos, the personification of death in Greek mythology.",
            R.drawable.thanos
        ),
        HeroDataUi(
            "Wolverine",
            "He is a mutant",
            R.drawable.wolverine
        ),
    )

    override fun obtainEvent(event: HeroesListEvent) {
        when (event) {
            HeroesListEvent.LoadHeroesList -> getHeroesList()
        }
    }

    private fun getHeroesList() {
        _viewState.value = HeroesListViewState(heroesList = testList)
    }
}
