package ru.flobsterable.effectiveLabs.presentation.screens.heroesList.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.ui.consts.heroesListItemViewPadding
import ru.flobsterable.effectiveLabs.presentation.models.HeroDataUi
import ru.flobsterable.effectiveLabs.utils.IntCallback

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroesListItemView(heroData: HeroDataUi, modifier: Modifier, onClick: IntCallback) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick(heroData.id) }
    ) {
        AsyncImage(
            model = heroData.imageUrl,
            contentDescription = stringResource(id = R.string.cd_character_image),
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(heroesListItemViewPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = heroData.name,
                style = MaterialTheme.typography.h2,
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HeroesListItemView(heroData = HeroDataUi("Name", "Description", "",0), Modifier) {}
}
