package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import ru.flobsterable.effectiveLabs.ui.theme.BlackScrim
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.R

@Composable
fun HeroInfoView(heroInfo: HeroDataUi) {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = heroInfo.imageUrl,
            contentDescription = stringResource(id = R.string.cd_character_image),
            modifier = Modifier.background(MaterialTheme.colors.primary).fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 68.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = heroInfo.name,
                style = MaterialTheme.typography.h1,
                color = Color.White,
                modifier = Modifier.background(BlackScrim)
            )
            Text(
                text = heroInfo.description,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.background(BlackScrim)
            )
        }
    }
}
