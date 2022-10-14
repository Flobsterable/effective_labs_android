package com.example.effective_labs.ui.screens.heroesList.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.effective_labs.R
import com.example.effective_labs.ui.HeroDataUi
import com.example.effective_labs.ui.consts.heroesListItemViewPadding
import com.example.effective_labs.utils.orientationModifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeroesListItemView(heroData: HeroDataUi) {

    val modifier = orientationModifier(
        landscapeModifier = Modifier
            .fillMaxHeight()
            .aspectRatio(3 / 2f),
        portraitModifier = Modifier
            .fillMaxHeight()
            .aspectRatio(7 / 15f)
    )

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { }
    ) {
        Image(
            painter = painterResource(heroData.imageId),
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
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HeroesListItemView(heroData = HeroDataUi("Name", "Description", 0))
}
