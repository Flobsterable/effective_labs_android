package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.presentation.utils.orientationValue
import ru.flobsterable.effectiveLabs.ui.consts.ITEM_RATIO_HEIGHT_LANDSCAPE
import ru.flobsterable.effectiveLabs.ui.consts.ITEM_RATIO_HEIGHT_PORTRAIT
import ru.flobsterable.effectiveLabs.ui.consts.ITEM_RATIO_WIDTH_LANDSCAPE
import ru.flobsterable.effectiveLabs.ui.consts.ITEM_RATIO_WIDTH_PORTRAIT
import ru.flobsterable.effectiveLabs.utils.IntCallback


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroesListRow(heroesList: List<HeroDataUi>, onClick: IntCallback) {

    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val maxWidth = LocalConfiguration.current.screenWidthDp
    val maxHeight = LocalConfiguration.current.screenHeightDp

    val itemScreenRatioWidth = orientationValue(ITEM_RATIO_WIDTH_LANDSCAPE, ITEM_RATIO_WIDTH_PORTRAIT) as Double
    val itemScreenRatioHeight = orientationValue(ITEM_RATIO_HEIGHT_LANDSCAPE, ITEM_RATIO_HEIGHT_PORTRAIT) as Double

    val itemWidth = (maxWidth*itemScreenRatioWidth)
    val itemHeight = (maxHeight*itemScreenRatioHeight)
    val padding = ((maxWidth/2)-itemWidth/2).dp

    LazyRow(
        state = lazyListState,
        flingBehavior = flingBehavior,
        contentPadding = PaddingValues(start = padding, end = padding),
        horizontalArrangement = Arrangement.spacedBy(26.dp),
    ) {
        items(heroesList) { item ->
            HeroesListItemView(item, Modifier.size(itemWidth.dp,itemHeight.dp), onClick)
        }
    }
}
