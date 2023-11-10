package ru.axas.spechrecognizer.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import ru.axas.spechrecognizer.base.common_composable.LoadBarWithTimerClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.axas.spechrecognizer.base.extension.clickableNoRipple
import ru.axas.spechrecognizer.base.theme.LocalGlobalData
import ru.axas.spechrecognizer.base.theme.ThemeApp
import ru.axas.spechrecognizer.screen.splash.SplashScreen

@Composable
fun MainNavigation() {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val globalData by rememberUpdatedState(newValue = LocalGlobalData.current)
    LaunchedEffect(globalData.messageSnack) {
        scope.launch {
            val text = globalData.messageSnack.value
            if (!text.isNullOrEmpty()) {
                snackBarHostState.showSnackbar(
                    message = text,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeApp.colors.background)
    ) {
        Navigator(
            onBackPressed = { false },
            screen = SplashScreen()
        )

        LoadBarWithTimerClose(isView = globalData.isLoad)

        SnackbarHost(
            modifier = Modifier
                .align(Alignment.Center)
                .imePadding(),
            hostState = snackBarHostState
        ) { snackBarData ->
            LaunchedEffect(key1 = null, block = {
                delay(2000)
                snackBarData.dismiss()
            })
            Snackbar(
                modifier = Modifier
                    .clickableNoRipple(onClick = remember(snackBarData) {
                        { snackBarData.dismiss() }
                    }),
                shape = ThemeApp.shape.mediumAll,
                snackbarData = snackBarData,
                containerColor = ThemeApp.colors.onBackgroundVariant,
                contentColor = ThemeApp.colors.backgroundVariant,
            )
        }
    }
}