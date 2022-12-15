package ru.flobsterable.effectiveLabs.presentation.screens.heroInfo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.flobsterable.effectiveLabs.utils.Callback
import ru.flobsterable.effectiveLabs.R
import ru.flobsterable.effectiveLabs.ui.theme.WhiteScrim

@Composable
fun HeroScreenTopBar(onBackClick: Callback) {
    TopAppBar(
        contentPadding = WindowInsets.systemBars.add(WindowInsets(left = 8.dp))
            .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)
            .asPaddingValues(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        IconButton(onClick = onBackClick, Modifier.clip(CircleShape).background(WhiteScrim)) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = MaterialTheme.colors.primary,
                contentDescription = stringResource(id = R.string.cd_button_back)
            )
        }
    }
}
