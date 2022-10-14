package com.example.effective_labs.ui.screens.heroesList.model

import com.example.effective_labs.R
import com.example.effective_labs.ui.HeroDataUi

data class HeroesListViewState(
    val heroesList: List<HeroDataUi> = listOf(
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
)
