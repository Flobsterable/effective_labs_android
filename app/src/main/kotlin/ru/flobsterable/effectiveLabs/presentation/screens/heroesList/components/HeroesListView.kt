package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.ui.consts.heroesListView
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.utils.IntCallback

@Composable
fun HeroesListView(heroList: List<HeroDataUi>, modifier: Modifier = Modifier, onClick: IntCallback) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.heroes_list_screen_text_label),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(heroesListView)
        )
        HeroesListRow(heroesList = heroList, onClick = onClick)
    }
}
