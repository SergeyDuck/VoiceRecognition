package com.example.voicerecognition.screen.home

import androidx.annotation.RawRes
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import com.example.voicerecognition.base.common_composable.BoxFillWeight
import com.example.voicerecognition.base.common_composable.BoxImageLoad
import com.example.voicerecognition.base.common_composable.BoxImageRowRes
import com.example.voicerecognition.base.common_composable.BoxSpacer
import com.example.voicerecognition.base.common_composable.IconApp
import com.example.voicerecognition.base.common_composable.IconButtonApp
import com.example.voicerecognition.base.common_composable.TextFieldOutlinesApp
import com.example.voicerecognition.base.extension.clickableRipple
import com.example.voicerecognition.base.res.DimApp
import com.example.voicerecognition.base.theme.ThemeApp
import com.example.voicerecognition.base.util.PermissionsModule
import com.example.voicerecognition.common.R
import com.example.voicerecognition.common.base.util.getQualifiedName
import com.example.voicerecognition.common.base.util.rememberImageRaw
import com.example.voicerecognition.common.base.util.rememberModel
import com.example.voicerecognition.common.base.util.rememberState
import com.example.voicerecognition.common.models.logger.LogCustom

class HomeMainScreen : Screen {

    override val key: ScreenKey = keyName

    companion object {
        val keyName = object {}::class.getQualifiedName
    }

    @Composable
    override fun Content() {
        val model = rememberModel<HomeMainModel>()
        HomeScr()
    }
}

@Composable
fun HomeScr() {
    var textFieldValue by rememberState { TextFieldValue("") }
    val recognizeIntent = PermissionsModule.recognizeIntent { str ->
        textFieldValue = TextFieldValue(str)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        BoxFillWeight()
        TextFieldOutlinesApp(modifier = Modifier
            .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {})
        BoxSpacer()
        BoxSpacer()
        BoxSpacer()
        IconButtonApp(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = recognizeIntent::invoke,
            rawImage = R.raw.ic_mic
        )
        BoxFillWeight()
    }
}

