package ru.axas.contacts.base.common_composable

import androidx.annotation.RawRes
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.axas.contacts.base.extension.clickableRipple
import ru.axas.contacts.base.res.DimApp
import ru.axas.contacts.base.theme.ThemeApp
import ru.axas.contacts.common.base.util.rememberImageRaw
import ru.axas.contacts.common.base.util.rememberState

@Composable
fun IconApp(
    painter: Painter,
    modifier: Modifier = Modifier,
    size: Dp? = DimApp.iconSizeStandard,
    tint: Color? = LocalContentColor.current
) {
    tint?.let {
        Icon(
            modifier = modifier.then(size?.let { Modifier.size(size) } ?: Modifier),
            painter = painter,
            contentDescription = null,
            tint = tint,
        )
    } ?: run {
        Box(modifier = modifier
            .then(size?.let { Modifier.size(size) } ?: Modifier)
            .paint(
                painter = painter,
                contentScale = ContentScale.Fit))
    }
}


@Composable
fun IconButtonApp(
    modifier: Modifier = Modifier,
    @RawRes rawImage: Int,
    onClick: () -> Unit,
) {
    val state = remember { MutableTransitionState(false).apply { targetState = false } }
    val tweenMillis = remember { 500 }
    val shadowElevationButton by rememberState { 1.dp }
    val shadowElevationButtonAnimate = animateDpAsState(
        targetValue = shadowElevationButton,
        animationSpec = remember(state.targetState) {
            tween(durationMillis = if (state.targetState) tweenMillis / 3 else 0)
        },
        label = "",
    )
    Box(
        modifier = modifier
            .size(DimApp.iconSizeTouchBig)
            .clip(CircleShape)
            .clickableRipple { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        IconApp(
            modifier = Modifier
                .shadow(
                    elevation = shadowElevationButtonAnimate.value,
                    shape = CircleShape
                )
                .background(ThemeApp.colors.backgroundVariant)
                .padding(DimApp.screenPadding * .3f),
            tint = ThemeApp.colors.primary,
            painter = rememberImageRaw(id = rawImage)
        )
    }
}
