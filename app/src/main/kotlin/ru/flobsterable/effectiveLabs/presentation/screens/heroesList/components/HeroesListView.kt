package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import ru.flobsterable.effectiveLabs.presentation.utils.orientationModifier


private const val LANDSCAPE_ITEM_RATIO = 3 / 2f
private const val PORTRAIT_ITEM_RATIO = 7 / 15f

@Composable
fun HeroesListView(heroList: List<HeroDataUi>, modifier: Modifier = Modifier, onClick: IntCallback) {

    val itemModifier = orientationModifier(
        landscapeModifier = Modifier
            .fillMaxHeight()
            .aspectRatio(LANDSCAPE_ITEM_RATIO),
        portraitModifier = Modifier
            .fillMaxHeight()
            .aspectRatio(PORTRAIT_ITEM_RATIO)
    )

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
        HeroesListRow(heroesList = heroList, modifier = itemModifier, onClick = onClick)
    }
}
