package com.example.effective_labs.ui.screens.heroesList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.effective_labs.R
import com.example.effective_labs.ui.screens.heroesList.view.HeroesListView
import com.example.effective_labs.utils.orientationModifier
import com.example.effective_labs.viewModels.HeroesListViewModel

@Composable
fun HeroesListScreen(viewModel: HeroesListViewModel) {

    val viewState = viewModel.viewState.observeAsState()

    Column(
        modifier = orientationModifier(
            landscapeModifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
            portraitModifier = Modifier.padding(top = 16.dp, bottom = 60.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = orientationModifier(
                landscapeModifier = Modifier
                    .fillMaxWidth(1 / 5f)
                    .padding(top = 8.dp, bottom = 8.dp),
                portraitModifier = Modifier
                    .fillMaxWidth(1 / 3f)
                    .padding(top = 36.dp, bottom = 16.dp)
            ),
            painter = painterResource(id = R.drawable.marvel),
            contentDescription = stringResource(id = R.string.cd_main_banner)
        )

        HeroesListView(viewState.value!!.heroesList)
    }
}
