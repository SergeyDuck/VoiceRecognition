package com.example.voicerecognition.screen.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import com.example.voicerecognition.base.common_composable.Logo
import com.example.voicerecognition.base.theme.ThemeApp
import com.example.voicerecognition.common.base.util.LifeScreen
import com.example.voicerecognition.common.base.util.getQualifiedName
import com.example.voicerecognition.common.base.util.rememberModel
import kotlinx.coroutines.delay

class SplashScreen: Screen {

    override val key: ScreenKey = keyName

    companion object {
        val keyName = object {}::class.getQualifiedName
    }

    @Composable
    override fun Content() {
        val model = rememberModel<SplashScreenModel>()
        LifeScreen(onStart = { model.startApp() })
        SplashScr()
    }
}

@Composable
fun SplashScr() {
    var isAnimatedStart by remember { mutableStateOf(true) }
    val durationMillis = remember { 2000 }

    val imageAlpha: Float by animateFloatAsState(
        targetValue = if (isAnimatedStart) .6f else 1f,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing,
        ), label = "SplashScr"
    )
    LaunchedEffect(Unit) {
        while (true) {
            isAnimatedStart = !isAnimatedStart
            delay(durationMillis.toLong())
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeApp.colors.primary)
    ) {
        Logo(alpha = imageAlpha)
    }
}