package com.example.effective_labs.ui.screens.heroesList.model

import com.example.effective_labs.R
import com.example.effective_labs.ui.HeroData

data class HeroesListViewState (
    val heroesList: List<HeroData> = listOf(
        HeroData("Iron Man", "Description", R.drawable.iron_man ),
        HeroData("Thanos", "Description", R.drawable.thanos ),
        HeroData("Wolverine", "Description", R.drawable.wolverine ),
    )
)