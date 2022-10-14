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
            "He is a mutant who possesses animal-keen senses, enhanced physical capabilities, a powerful regenerative ability known as a healing factor, and three retractable claws in each hand",
            R.drawable.wolverine
        ),
    )
)
