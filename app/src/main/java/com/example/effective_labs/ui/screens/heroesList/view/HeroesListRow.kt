package com.example.effective_labs.ui.screens.heroesList.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.example.effective_labs.ui.HeroData
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun HeroesListRow(heroesList: List<HeroData>) {

    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem
            Log.e("HeroesListRow", "$snappedItem")
        }
    }

    BoxWithConstraints {
        LazyRow(
            state = lazyListState,
            flingBehavior = rememberSnapperFlingBehavior(
                lazyListState,
                snapOffsetForItem = SnapOffsets.Center,
            ),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            itemsIndexed(heroesList) { index, item ->

                Layout(
                    content = {
                        HeroesListItemView(item)
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
}