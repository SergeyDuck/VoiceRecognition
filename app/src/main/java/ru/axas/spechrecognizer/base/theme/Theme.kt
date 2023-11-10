package ru.axas.spechrecognizer.base.theme

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
import ru.axas.spechrecognizer.base.res.LightPaletteApp
import ru.axas.spechrecognizer.base.res.ShapesApp
import ru.axas.spechrecognizer.base.res.TypographyApp
import ru.axas.spechrecognizer.common.base.util.rememberImeHeight
import ru.axas.spechrecognizer.common.base.util.rememberNavigationBarHeight
import ru.axas.spechrecognizer.common.base.util.rememberState
import ru.axas.spechrecognizer.common.base.util.rememberStatusBarHeight
import ru.axas.spechrecognizer.common.memory.GlobalDada
import ru.axas.spechrecognizer.common.models.local.DataSingleLive

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


