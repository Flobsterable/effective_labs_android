package com.example.effective_labs.viewModels

import androidx.lifecycle.ViewModel
import com.example.effective_labs.ui.screens.heroesList.model.HeroesListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HeroesListViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(HeroesListViewState())
    val viewState: StateFlow<HeroesListViewState> = _viewState.asStateFlow()
}
