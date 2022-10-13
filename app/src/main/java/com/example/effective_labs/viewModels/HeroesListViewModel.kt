package com.example.effective_labs.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.effective_labs.ui.screens.heroesList.model.HeroesListViewState

class HeroesListViewModel : ViewModel() {


    private val _viewState = MutableLiveData(HeroesListViewState())
    val viewState: LiveData<HeroesListViewState> = _viewState
}