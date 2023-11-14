package ru.axas.contacts.base.common_composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.axas.contacts.base.theme.ThemeApp
import ru.axas.contacts.base.res.DimApp
import ru.axas.contacts.common.models.res.TextApp


@Composable
fun BoxScope.Logo(alpha: Float = 1f) {
    Row(
        modifier = Modifier
            .align(Alignment.Center),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            style = ThemeApp.typography.titleLarge.copy(fontSize = DimApp.fontSplashSize),
            text = TextApp.baseTextNameApp,
            color = ThemeApp.colors.onPrimary.copy(alpha = alpha)
        )
    }
}