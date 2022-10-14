package com.example.effective_labs.ui.screens.heroesList.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.effective_labs.R
import com.example.effective_labs.ui.HeroDataUi

@Composable
fun HeroesListView(heroList: List<HeroDataUi>) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.heroes_list_screen_text_label),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        HeroesListRow(heroesList = heroList)
    }
}
