package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.utils.IntCallback


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesListRow(heroesList: List<HeroDataUi>, modifier: Modifier, onClick: IntCallback) {

    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val maxWidth = LocalConfiguration.current.screenWidthDp.dp

    LazyRow(
        state = lazyListState,
        flingBehavior = flingBehavior,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(heroesList) { index, item ->

            Layout(
                content = {
                    HeroesListItemView(item, modifier, onClick)
                },
                measurePolicy = { measurables, constraints ->
                    val placeable = measurables.first().measure(constraints)
                    val maxWidthInPx = maxWidth.roundToPx()
                    val itemWidth = placeable.width
                    val startSpace =
                        if (index == 0) (maxWidthInPx - itemWidth) / 2 else 0
                    val endSpace =
                        if (index == heroesList.lastIndex) (maxWidthInPx - itemWidth) / 2 else 0
                    val width = startSpace + placeable.width + endSpace
                    layout(width, placeable.height) {
                        val x = if (index == 0) startSpace else 0
                        placeable.place(x, 0)
                    }
                }
            )
        }
    }
}
