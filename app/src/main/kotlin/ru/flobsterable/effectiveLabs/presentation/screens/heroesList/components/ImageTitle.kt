package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.presentation.utils.isLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListImagePaddingLandscape
import ru.flobsterable.effectiveLabs.ui.consts.heroesListImagePaddingPortrait

private const val LANDSCAPE_IMAGE_WIDTH = 0.2f
private const val PORTRAIT_IMAGE_WIDTH = 1 / 3f

@Composable
fun ImageTitle() {
    Image(
        modifier = when(isLandscape()) {
            true -> Modifier
                .fillMaxWidth(LANDSCAPE_IMAGE_WIDTH)
                .padding(heroesListImagePaddingLandscape)
            false -> Modifier
                .fillMaxWidth(PORTRAIT_IMAGE_WIDTH)
                .padding(heroesListImagePaddingPortrait) },
        painter = painterResource(id = R.drawable.marvel),
        contentDescription = stringResource(id = R.string.cd_main_banner)
    )
}
