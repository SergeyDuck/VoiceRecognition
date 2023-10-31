package com.example.voicerecognition.base.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import com.example.voicerecognition.base.res.LightPaletteApp
import com.example.voicerecognition.base.res.ShapesApp
import com.example.voicerecognition.base.res.TypographyApp
import com.example.voicerecognition.common.base.util.rememberImeHeight
import com.example.voicerecognition.common.base.util.rememberNavigationBarHeight
import com.example.voicerecognition.common.base.util.rememberState
import com.example.voicerecognition.common.base.util.rememberStatusBarHeight
import com.example.voicerecognition.common.memory.GlobalDada
import com.example.voicerecognition.common.models.local.DataSingleLive

@Composable
fun AxasTheme(
    colors: ColorsSchemeApp = LightPaletteApp,
    typography: TypographySchemeApp = TypographyApp,
    shape: ShapeSchemeApp = ShapesApp,
    content: @Composable () -> Unit
) {
    var globalData by rememberState { GlobalDada.value ?: DataSingleLive() }
    DisposableEffect(GlobalDada) {
        val observer = Observer<DataSingleLive> { globalData = it }
        GlobalDada.observeForever(observer)
        onDispose {
            GlobalDada.removeObserver(observer)
        }
    }
    val view = LocalView.current

    val statusBarsWindow = rememberStatusBarHeight()
    val navigationBarsWindow = rememberNavigationBarHeight()
    val imeWindow = rememberImeHeight()

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primary.copy(.5f).toArgb()
            window.navigationBarColor = colors.primary.copy(.8f).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightNavigationBars = false
        }
    }
    CompositionLocalProvider(
        LocalColorsApp provides colors,
        LocalTypographyApp provides typography,
        LocalShapeApp provides shape,
        LocalGlobalData provides globalData,
        LocalFixedInsets provides FixedInsets(
            statusBar = statusBarsWindow,
            navigationBars = navigationBarsWindow,
            imeWindow = imeWindow
        ),
        content = content
    )
}


val LocalFixedInsets = compositionLocalOf<FixedInsets> { error("no FixedInsets provided!") }

data class FixedInsets(
    val statusBar: Int = 0,
    val navigationBars: Int = 0,
    val imeWindow: Int = 0,
)

val LocalGlobalData = staticCompositionLocalOf { DataSingleLive() }


