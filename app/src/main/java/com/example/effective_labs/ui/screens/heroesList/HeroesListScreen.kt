package com.example.effective_labs.ui.screens.heroesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.effective_labs.R
import com.example.effective_labs.ui.screens.heroesList.view.HeroesListView
import com.example.effective_labs.ui.consts.heroesListColumnPaddingLandscape
import com.example.effective_labs.ui.consts.heroesListColumnPaddingPortrait
import com.example.effective_labs.ui.consts.heroesListImagePaddingLandscape
import com.example.effective_labs.ui.consts.heroesListImagePaddingPortrait
import com.example.effective_labs.utils.orientationModifier
import com.example.effective_labs.viewModels.HeroesListViewModel

@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel) {

    val viewState = viewModel.viewState.collectAsState()

    Column(
        modifier = orientationModifier(
            landscapeModifier = Modifier.padding(heroesListColumnPaddingLandscape),
            portraitModifier = Modifier.padding(heroesListColumnPaddingPortrait)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = orientationModifier(
                landscapeModifier = Modifier
                    .fillMaxWidth(1 / 5f)
                    .padding(heroesListImagePaddingLandscape),
                portraitModifier = Modifier
                    .fillMaxWidth(1 / 3f)
                    .padding(heroesListImagePaddingPortrait)
            ),
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = stringResource(id = R.string.cd_main_banner)
        )

        HeroesListView(viewState.value.heroesList)
    }
}
